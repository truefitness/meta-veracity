DESCRIPTION = "QR Code encoding library"
HOMEPAGE = "http://fukuchi.org/works/qrencode/index.html"
LICENSE = "LGPLv2"

inherit autotools
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "git://github.com/fukuchi/libqrencode.git;protocol=git \
          "

DEPENDS = "libsdl"

S = "${WORKDIR}/git"
SRCREV = "baac80aefd97cadc8cfb8a11e0f3aff208de267f"
