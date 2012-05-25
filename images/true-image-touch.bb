#
# Copyright (C) 2012 True Fitness Technologies
#
DESCRIPTION = "Veracity Touch console image." 

IMAGE_FEATURES += "apps-console-core ${X11_IMAGE_FEATURES} package-manager x11-mini \
qt4-x11-free \
ssh-server-dropbear \
"
LICENSE = "MIT"

RDEPENDS_${PN} += " truetouch mesa-dri xf86-video-intel xf86-video-fbdev"

IMAGE_INSTALL += " ${CORE_IMAGE_BASE_INSTALL} truetouch mesa-dri tslib xtscal xvinfo wireless-tools wpa-supplicant xf86-video-intel xf86-video-fbdev"

#IMAGE_LINGUAS = " "


inherit core-image

#IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
