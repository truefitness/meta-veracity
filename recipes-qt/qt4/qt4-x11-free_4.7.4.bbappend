FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://patch-config.tests-unix-fvisibility.test.patch "

# Add flags to Qt4 configure to build for opengl ES2.0
#QT_GLFLAGS += "-opengl es2"

