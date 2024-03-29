DESCRIPTION  = "GStreamer bindings for Qt"

LICENSE  = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS  = "gstreamer gst-plugins-base boost boost-native qt4-x11-free"
PR = "r1"
SRCREV = "33d5a410fc7e7997a9c4ebf76ae6500fb4f6b321"

inherit cmake

SRC_URI = "git://anongit.freedesktop.org/gstreamer/qt-gstreamer;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = " -DLIB_INSTALL_DIR:PATH=${libdir} -DQT_IMPORTS_DIR:PATH=${libdir}/qt4/imports -DQTGSTREAMER_EXAMPLES=OFF -DQTGSTREAMER_TESTS=OFF -DQTGSTREAMER_CODEGEN=OFF -DUSE_GST_PLUGIN_DIR=OFF"

FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug ${libdir}/qt4/imports/QtGStreamer/.debug"

FILES_${PN} += "${libdir}/gstreamer-0.10 \
                ${libdir}/QtGStreamer/* \
                ${libdir}/qt4/imports/QtGStreamer "


# Generate a qt.conf file to point to the correct mkspec for qmake.  Need target mkspec
# Stolen from classes/qmake_base.bbclass
do_generate_qt_config_file() {
    export QT_CONF_PATH=${WORKDIR}/qt.conf
    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Prefix =
Binaries = ${STAGING_BINDIR_NATIVE}
Headers = ${STAGING_INCDIR}/qt4
Plugins = ${STAGING_LIBDIR}/qt4/plugins/
Libraries = ${STAGING_LIBDIR}
Mkspecs = ${STAGING_DATADIR}/qt4/mkspecs/
EOF
}

addtask generate_qt_config_file after do_patch before do_configure

PACKAGE_ARCH = "${MACHINE_ARCH}"

