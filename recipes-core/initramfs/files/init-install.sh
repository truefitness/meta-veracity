#!/bin/sh -e
#
# Copyright (C) 2008-2011 Intel
#
# install.sh [device_name] [rootfs_name] [video_mode] [vga_mode]
#

PATH=/sbin:/bin:/usr/sbin:/usr/bin

# We need room in the boot partition for 2 revisions of the root file system and kernels
boot_size=640

# Size of configuration data partition
config_size=8

# % of disk for the swap partition
swap_ratio=5
swap_size=128

found="no"

echo "Searching for a hard drive..."
for device in 'hda' 'hdb' 'sda' 'sdb' 'mmcblk0' 'mmcblk1'
  do
  if [ -e /sys/block/${device}/removable ]; then
      if [ "$(cat /sys/block/${device}/removable)" = "0" ]; then
	  found="yes"

	  while true; do
	      # Try sleeping here to avoid getting kernel messages
              # obscuring/confusing user
	      #sleep 5
	      echo "Found drive at /dev/${device}. Do you want to install this image there ? [y/n]"
	      read answer
	      if [ "$answer" = "y" ] ; then
		  break
	      fi
	  
	      if [ "$answer" = "n" ] ; then
		  found=no
		  break
	      fi

	      echo "Please answer by y or n"
	  done
      fi
  fi

  if [ "$found" = "yes" ]; then
      break;
  fi

done

if [ "$found" = "no" ]; then
      exit 1      
fi

echo "Installing image on /dev/${device}"

#
# The udev automounter can cause pain here, kill it
#
rm -f /etc/udev/scripts/mount*

#
# Unmount anything the automounter had mounted
#
umount /dev/${device}* 2> /dev/null || /bin/true

if [ ! -b /dev/sda ] ; then
    mknod /dev/sda b 8 0
fi

if [ ! -b /dev/sdb ] ; then
    mknod /dev/sdb b 8 16
fi

if [ ! -b /dev/loop0 ] ; then
    mknod /dev/loop0 b 7 0
fi

mkdir -p /tmp
cat /proc/mounts > /etc/mtab

disk_size=$(parted /dev/${device} unit mb print | grep Disk | cut -d" " -f 3 | sed -e "s/MB//")

#swap_size=$((disk_size*5/100))
# data partition size is what is left
data_size=$((disk_size-boot_size-config_size-swap_size))

# compute start and end of each partition: boot, swap, config, data
boot_start=1
boot_end=$((boot_start + boot_size))

swap_start=$((boot_end+1))
swap_end=$((swap_start + swap_size))

config_start=$((swap_end + 1))
config_end=$((config_start + config_size))

data_start=$((config_end + 1))
data_end=$disk_size
#$((data_start + data_size))


# MMC devices are special in a couple of ways
# 1) they use a partition prefix character 'p'
# 2) they are detected asynchronously (need rootwait)
rootwait=""
part_prefix=""
if [ ! "${device#mmcblk}" = "${device}" ]; then
	part_prefix="p"
	rootwait="rootwait"
fi
bootfs=/dev/${device}${part_prefix}1
swap=/dev/${device}${part_prefix}2
configfs=/dev/${device}${part_prefix}3
datafs=/dev/${device}${part_prefix}4

echo "*****************"
echo "Disk size          :   $disk_size MB ($device)"
echo "Boot partition size:   $boot_size MB ($bootfs)"
echo "Swap partition size:   $swap_size MB ($swap)"
echo "Config partition size: $config_size MB ($configfs)"
echo "Data partition size: $data_size MB ($datafs)"
echo "*****************"

echo "Proceed? (y/n)"
read answer
if [ "$answer" != "y" ] ; then
  exit
fi
	  
if [ false ] ; then
echo "Deleting partition table on /dev/${device} ..."
dd if=/dev/zero of=/dev/${device} bs=512 count=2

echo "Creating new partition table on /dev/${device} ..."
parted /dev/${device} mklabel msdos

echo "Creating boot partition on $bootfs"
parted /dev/${device} mkpart primary 1 $boot_size

echo "Creating swap partition on $swap"
parted /dev/${device} mkpart primary $swap_start $swap_end

echo "Creating config partition on $configfs"
parted /dev/${device} mkpart primary $config_start $config_end

echo "Creating data partition on $datafs"
parted /dev/${device} mkpart primary $data_start $data_end

parted /dev/${device} print

echo "Formatting $bootfs to ext3..."
mkfs.ext3 $bootfs

echo "Formatting $configfs to ext3..."
mkfs.ext3 $configfs

echo "Formatting $datafs to ext3..."
mkfs.ext3 $datafs

echo "Formatting swap partition...($swap)"
mkswap $swap

fi

mkdir -p /root
mkdir -p /datamnt
mkdir -p /configmnt
mkdir -p /boot

rootimg_srcfile=/media/$1/$2
kernel_srcfile=/media/$1/vmlinuz
initrd_srcfile=/media/$1/initrd

mount $bootfs /boot
#mkdir -p /boot/boot

echo "Copying rootfs image file..."
cp -a $rootimg_srcfile /boot
echo "Copying kernel image file..."
cp -a $kernel_srcfile /boot
echo "Copying initrd image file..."
cp -a $initrd_srcfile /boot

# mount the new root image file
mount -o rw,loop,noatime,nodiratime /boot/$2 /root

# At this point, we're modifying the rootfs.img file...Maybe /etc/ stuff can go in the configfs?

if [ -d /root/etc/ ] ; then
    echo "$swap                swap             swap       defaults              0  0" >> /root/etc/fstab

    # We dont want udev to mount our root device while we're booting...
    if [ -d /root/etc/udev/ ] ; then
	echo "/dev/${device}" >> /root/etc/udev/mount.blacklist
    fi
fi

# NEED TO FIX THIS:  ROOTFS needs to be initrd??
# do we need grub.d in the root image at all or just the boot partition?
if [ -f /root/etc/grub.d/40_custom ] ; then
    echo "Preparing custom grub2 menu..."
    sed -i "s@__ROOTFS__@$rootfs $rootwait@g" /root/etc/grub.d/40_custom
    sed -i "s/__VIDEO_MODE__/$3/g" /root/etc/grub.d/40_custom
    sed -i "s/__VGA_MODE__/$4/g" /root/etc/grub.d/40_custom
    cp /root/etc/grub.d/40_custom /boot/40_custom
fi

umount /root
sync
# unmount the rootfs image loopback forceably
losetup -d /dev/loop0
umount /boot

echo "Preparing boot partition..."
mount $bootfs /boot
grub-install --root-directory=/ /dev/${device}

echo "(hd0) /dev/${device}" > /boot/grub/device.map

if [ -f /boot/40_custom ] ; then
    mv /boot/40_custom /boot/grub/grub.cfg
    sed -i "/#/d" /boot/grub/grub.cfg
    sed -i "/exec tail/d" /boot/grub/grub.cfg
    chmod 0444 /boot/grub/grub.cfg
else
    echo "Preparing custom grub menu..."
    echo "default 0" > /boot/grub/menu.lst
    echo "timeout 30" >> /boot/grub/menu.lst
    echo "title Live Boot/Install-Image" >> /boot/grub/menu.lst
    echo "root  (hd0,0)" >> /boot/grub/menu.lst
    #echo "kernel /boot/vmlinuz root=$rootfs rw $3 $4 quiet" >> /boot/grub/menu.lst
    echo "kernel /boot/vmlinuz root=/initrd rw $3 $4 quiet" >> /boot/grub/menu.lst
fi

umount /boot
sync

echo "Remove your installation media, and press ENTER"

read enter

echo "Rebooting..."
reboot -f
