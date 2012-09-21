
# Enable building egl-info

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://gles2-info.patch "
