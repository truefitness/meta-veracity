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

PREFERRED_VERSION_qt4-x11-free = "4.8.0"
PREFERRED_VERSION_qt4-native = "4.8.0"

VERACITY_QT_PACKAGES = "sqlite3 qt4-plugin-sqldriver-sqlite qt4-plugin-graphicssystems-glgraphicssystem"
VERACITY_DEV_PACKAGES = "xvinfo mesa-demos"
VERACITY_TOUCH_PACKAGES = "truetouch truetouch-locale"
VERACITY_VIDEO_PACKAGES = "gst-plugins-good-video4linux2 gst-plugins-base-xvimagesink gst-plugins-good-deinterlace \
	gst-plugins-base-ffmpegcolorspace gstreamer-vaapi "

RDEPENDS_${PN} += " truetouch mesa-dri "

IMAGE_INSTALL += " ${CORE_IMAGE_BASE_INSTALL} ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${VERACITY_QT_PACKAGES} \
		   ${VERACITY_TOUCH_PACKAGES} ${VERACITY_DEV_PACKAGES} ${VERACITY_VIDEO_PACKAGES} \
                    linux-firmware"

#need these? xvinfo wireless-tools wpa-supplicant"

IMAGE_LINGUAS = " "

# Live image setup (.hddimg), for booting or installing from a USB stick
# Menu timeout in tenths of a second
SYSLINUX_TIMEOUT ?= "50"

# The initial ramdisk to use
INITRD_IMAGE = "true-image-initramfs"

inherit core-image

# add a boot menu item to drop to shell after mounting initrd (see initrd init script in true-image-initramfs)
SYSLINUX_LABELS_prepend = "live shell "

IMAGE_ROOTFS_EXTRA_SPACE = "8192"

# remove not needed ipkg informations
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
