# Add overrides for configuration files in /etc

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Add additional mount points to the root filesystem image

dirs755 += "mnt/data mnt/config"
