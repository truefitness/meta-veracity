DESCRIPTION = "Truetouch is the application running on the Veracity console."
HOMEPAGE = "http://www.truefitness.com/"

LICENSE = "CLOSED"
SECTION = "x11/apps"

PR = "r0"

DEPENDS = "qt4-x11-free qt-mobility-x11"
RRECOMMENDS_${PN} += "qt4-plugin-imageformat-gif qt4-plugin-imageformat-jpeg qt4-plugin-imageformat-tiff"

# local git repository for development
TRUETOUCH_SRC_LOCAL ?= "/home/rwalden/work/Veracity"

# github repository for console application
#SRC_URI = "git://git@github.com/truefitness/Veracity.git;protocol=ssh"
SRC_URI = "git://${TRUETOUCH_SRC_LOCAL};protocol=file;branch=pokydev"
SRC_URI += " file://truetouch-session"

SRCREV = "${AUTOREV}"
#SRCREV = "41c89a21db959e77bbb77f2ab9ade85bee1f5933"

#SRC_URI = "file://truetouch.zip"

# Touch application under the "touch" subdirectory
S = "${WORKDIR}/git/touch"
inherit qmake2 pkgconfig
#EXTRA_QMAKEVARS_PRE = "CONFIG+=no-webcam"

APPDIR= "/usr/local/bin/truetouch"
FILES_${PN} = "\
                ${APPDIR}/* \
		${sysconfdir}/X11/Xsession.d/99app.sh \
                "

do_install() {
	oe_runmake INSTALL_ROOT=${D} install
	install -d ${D}${sysconfdir}/X11/Xsession.d
	install -m 644 ${S}/*.qm ${D}${APPDIR}
	install -m 0755 ${WORKDIR}/truetouch-session ${D}${sysconfdir}/X11/Xsession.d/99app.sh
}

CONFFILES_${PN} = "${sysconfdir}/X11/Xsession.d/99app.sh"
