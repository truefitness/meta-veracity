#
# Copyright (C) 2012 True Fitness Technologies
#
DESCRIPTION = "Veracity Touch console image." 

IMAGE_FEATURES += "apps-console-core package-management \
x11-base \
qt4-x11-free \
"
# These aren't features, which is why they won't install.  theyre recipes!
#fotowall \
#truetouch-session truetouch \
#"


RDEPENDS_${PN} = " truetouch"

#${X11_IMAGE_FEATURES} \
#qt4-pkgs \
# x11-base qt4-pkgs nfs nfsclient"
IMAGE_INSTALL = " ${CORE_IMAGE_BASE_INSTALL} truetouch fotowall"
#IMAGE_INSTALL = "task-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

#LICENSE = "MIT"


inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
