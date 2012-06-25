FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_cedar = "git://git.yoctoproject.org/linux-yocto-3.2;protocol=git;bareclone=1;branch=${KBRANCH},meta;name=machine,meta \
file://kernel-ttm-clear-high.patch \
file://cedarview-kernel-v1.0.1_bee.patch \
file://wlan.cfg \
file://vid.cfg \
file://cdv-config.patch \
file://machine.cfg \
file://usb.cfg \
file://firmware.cfg \
file://backlight.cfg \
file://drives.cfg \
"

# add configuration to build wireless lan drivers for Intel Centrino N 6000

COMPATIBLE_MACHINE_cedar = "cedar"
KMACHINE_cedar  = "cedar"
KBRANCH_cedar  = "standard/default/base"
#KERNEL_FEATURES_append_cedar += "bsp/cedartrail/cedartrail-pvr-merge.scc"
KERNEL_FEATURES_append_cedar += "cfg/efi-ext.scc"

KERNEL_REVISION_CHECKING = "0"
SRCREV_machine_pn-linux-yocto_cedar ?= "76133a1cadf0de417c29ed15d6fbb12c41c0802b"
#SRCREV_machine_pn-linux-yocto_cedar ?= "e08d1500ecf1f304fac380a70fc660d5f930686c"
#SRCREV_machine_pn-linux-yocto_cedar ?= "${AUTOREV}"
#SRCREV_meta_pn-linux-yocto_cedar ?= "ee78519365bdb25287703bbc31c06b193263c654"
SRCREV_meta_pn-linux-yocto_cedar ?= "ee78519365bdb25287703bbc31c06b193263c654"
#SRCREV_pvr_pn-linux-yocto_cedartrail ?= "997f5644664b31ebefd6e16c451c4a3729eb378a"

