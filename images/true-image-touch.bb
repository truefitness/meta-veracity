#
# Copyright (C) 2012 True Fitness Technologies
#
DESCRIPTION = "Veracity Touch console image." 

require recipes-core/images/core-image-core.bb
IMAGE_FEATURES += "apps-console-core package-management ${X11_IMAGE_FEATURES} x11-mini qt4-pkgs"
#IMAGE_INSTALL = "task-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

#IMAGE_INSTALL += "qt4-x11-free "

IMAGE_LINGUAS = " "

#LICENSE = "MIT"

#inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
