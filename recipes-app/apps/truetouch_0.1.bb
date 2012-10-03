DESCRIPTION = "Truetouch is the application running on the Veracity console."
HOMEPAGE = "http://www.truefitness.com/"

LICENSE = "CLOSED"
SECTION = "x11/apps"

PR = "r4"

DEPENDS = "qt4-x11-free qt-mobility-x11 libqrencode qt-gstreamer"
RDEPENDS = "gstreamer gst-plugins-good gst-ffmpeg sqlite3"
RRECOMMENDS_${PN} += "qt4-plugin-imageformat-gif qt4-plugin-imageformat-jpeg qt4-plugin-imageformat-tiff"

# uncomment the followint 2 lines instead of first git SRC_URI to use local git repository for application development
#TRUETOUCH_SRC_LOCAL ?= "/home/likewise-open/TRUEDOM/rwalden/projects/true/Veracity"
#SRC_URI = "git://${TRUETOUCH_SRC_LOCAL};protocol=file"

# github repository for console application
SRC_URI = "git://git@github.com/truefitness/Veracity.git;protocol=ssh"
SRC_URI += " file://truetouch-session"
SRC_URI += " file://nodeploy.patch"

# use the HEAD of the selected branch instead of a specific commit
SRCREV = "${AUTOREV}"

# Touch application under the "touch" subdirectory
S = "${WORKDIR}/git/touch"
inherit qmake2 pkgconfig

APPDIR= "/usr/local/bin/truetouch"

# place debugging and translation files in separate packages
PACKAGES = "${PN}-dbg ${PN}-locale ${PN}"

FILES_${PN}-locale += "${APPDIR}/*.qm"
FILES_${PN}-dbg += "${APPDIR}/.debug"
FILES_${PN} = "${APPDIR}/* \
		${sysconfdir}/X11/Xsession.d/89app.sh \
                "

do_install() {
	oe_runmake INSTALL_ROOT=${D} install
	install -d ${D}${sysconfdir}/X11/Xsession.d
	install -m 644 ${S}/*.qm ${D}${APPDIR}
	install -m 0755 ${WORKDIR}/truetouch-session ${D}${sysconfdir}/X11/Xsession.d/89app.sh
}

CONFFILES_${PN} = "${sysconfdir}/X11/Xsession.d/89app.sh"
