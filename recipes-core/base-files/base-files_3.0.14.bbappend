# Add overrides for configuration files in /etc

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Add additional mount points to the root filesystem image

do_install_append() {
     install -m 0755 -d ${D}/boot
     install -m 0755 -d ${D}/mnt/data
     install -m 0755 -d ${D}/mnt/config
}
