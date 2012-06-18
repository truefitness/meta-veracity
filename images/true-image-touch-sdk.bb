#
# Copyright (C) 2012 True Fitness Technologies
#
DESCRIPTION = "Veracity Touch console image with development tools." 


IMAGE_FEATURES += " dev-pkgs tools-sdk"
IMAGE_INSTALL += " nfs-utils nfs-utils-client"

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE = "8192"

require true-image-touch.bb
