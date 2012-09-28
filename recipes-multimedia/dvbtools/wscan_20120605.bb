DESCRIPTION = "Wscan is a dvb channel scanner that doesn't require an initial frequency table"
LICENSE = "GPLv2"

PR = "r1"

inherit autotools

LICENSE  = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://wirbel.htpc-forum.de/w_scan/w_scan-${PV}.tar.bz2"
S = "${WORKDIR}/w_scan-${PV}"

TARGET_CC_ARCH += "${LDFLAGS}"

#do_install() {
#    install -d ${D}/${bindir}
#    install -m 0755 w_scan ${D}/${bindir}/

#    install -d ${D}/${datadir}/w_scan
#    install -m 0644 *.ids *.classes  ${D}/${datadir}/w_scan/
#}

FILES_${PN} += "${datadir}"

SRC_URI[md5sum] = "6717b8ac27913f12746ca7e3e8299758"
SRC_URI[sha256sum] = "f22d98819a080d2c25a318f04cdc82c8c2d6748bfad15874e8564af49a70921b"

