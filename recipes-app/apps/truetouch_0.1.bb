DESCRIPTION = "Truetouch is the application running on the Veracity console."
HOMEPAGE = "http://www.truefitness.com/"

LICENSE = "CLOSED"
SECTION = "x11/apps"

PR = "r0"

DEPENDS = "qt4-x11-free qt-mobility-x11"
RRECOMMENDS_${PN} += "qt4-plugin-imageformat-gif qt4-plugin-imageformat-jpeg qt4-plugin-imageformat-tiff"

# github repository for console application
SRC_URI = "git://git@github.com/truefitness/Veracity.git;protocol=ssh"
SRCREV = "41c89a21db959e77bbb77f2ab9ade85bee1f5933"

#SRC_URI = "file://truetouch.zip"

# Touch application under the "touch" subdirectory
S = "${WORKDIR}/git/touch"
inherit qmake2 pkgconfig
#EXTRA_QMAKEVARS_PRE = "CONFIG+=no-webcam"

APPDIR= "/usr/local/bin/truetouch"
FILES_${PN} = "\
                ${APPDIR}/* \
                "

do_install() {
	oe_runmake INSTALL_ROOT=${D} install
}
