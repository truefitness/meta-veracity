FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# add configuration to build wireless lan drivers for Intel Centrino N 6000
SRC_URI += "\
file://wlan.cfg \
file://vid.cfg \
file://multitouch.cfg \
"
