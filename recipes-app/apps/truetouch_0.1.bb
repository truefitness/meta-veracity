DESCRIPTION = "Truetouch is the application running on the Veracity console."
HOMEPAGE = "http://www.truefitness.com/"

LICENSE = "CLOSED"
SECTION = "x11/apps"

PR = "r1"

DEPENDS = "qt4-x11-free qt-mobility-x11"
RRECOMMENDS_${PN} += "qt4-plugin-imageformat-gif qt4-plugin-imageformat-jpeg qt4-plugin-imageformat-tiff"

# uncomment the followint 2 lines instead of first git SRC_URI to use local git repository for application development
#TRUETOUCH_SRC_LOCAL ?= "/home/rwalden/work/Veracity"
#SRC_URI = "git://${TRUETOUCH_SRC_LOCAL};protocol=file;branch=pokydev"

# github repository for console application
SRC_URI = "git://git@github.com/truefitness/Veracity.git;protocol=ssh"
SRC_URI += " file://truetouch-session"

# use the HEAD of the selected branch instead of a specific commit
SRCREV = "${AUTOREV}"

# Touch application under the "touch" subdirectory
S = "${WORKDIR}/git/touch"
inherit qmake2 pkgconfig

APPDIR= "/usr/local/bin/truetouch"
FILES_${PN} = "\
                ${APPDIR}/* \
		${sysconfdir}/X11/Xsession.d/89app.sh \
                "

do_install() {
	oe_runmake INSTALL_ROOT=${D} install
	install -d ${D}${sysconfdir}/X11/Xsession.d
	install -m 644 ${S}/*.qm ${D}${APPDIR}
	install -m 0755 ${WORKDIR}/truetouch-session ${D}${sysconfdir}/X11/Xsession.d/89app.sh
}

CONFFILES_${PN} = "${sysconfdir}/X11/Xsession.d/89app.sh"
