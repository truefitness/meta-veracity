FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
COMPATIBLE_MACHINE_veracity = "veracity"
KMACHINE_veracity  = "veracity"

KERNEL_FEATURES_append_veracity += " cfg/smp.scc"
KERNEL_FEATURES_append_veracity += " cfg/drm-cdvpvr.scc"
KERNEL_FEATURES_append_veracity += " bsp/cedartrail/cedartrail-pvr-merge.scc"
KERNEL_FEATURES_append_veracity += " cfg/efi-ext.scc"

COMPATIBLE_MACHINE_veracity-nopvr = "veracity"
KMACHINE_veracity-nopvr  = "veracity"
KERNEL_FEATURES_append_veracity-nopvr += " cfg/smp.scc"


# Update the following to use a different BSP branch or meta SRCREV
#KBRANCH_veracity  = "yocto/standard/preempt-rt/base"
#SRCREV_machine_pn-linux-yocto-rt_veracity ?= XXXX
#SRCREV_meta_pn-linux-yocto-rt_veracity ?= XXXX
