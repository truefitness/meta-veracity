#
# Copyright (C) 2012 True Fitness Technologies
#
DESCRIPTION = "Veracity Touch console image." 

IMAGE_FEATURES += "apps-console-core ${X11_IMAGE_FEATURES} package-manager x11-mini \
qt4-x11-free \
touchscreen wifi \
ssh-server-dropbear \
"
LICENSE = "MIT"

RDEPENDS_${PN} += " truetouch mesa-dri "

IMAGE_INSTALL += " ${CORE_IMAGE_BASE_INSTALL} ${ROOTFS_PKGMANAGE_BOOTSTRAP} truetouch"

#mesa-dri tslib xtscal xvinfo wireless-tools wpa-supplicant"

IMAGE_LINGUAS = " "

# Live image setup (.hddimg), for booting or installing from a USB stick
# Menu timeout in tenths of a second
SYSLINUX_TIMEOUT ?= "50"

# The initial ramdisk to use
INITRD_IMAGE = "true-image-initramfs"

inherit core-image

# add a boot menu item to drop to shell after mounting initrd (see initrd init script in true-image-initramfs)
SYSLINUX_LABELS_append = " live shell"

#IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
