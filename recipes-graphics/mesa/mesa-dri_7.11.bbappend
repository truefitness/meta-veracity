# Add gles options to build of Mesa

DRIDRIVERS = "swrast"
DRIDRIVERS_append_x86 = ",i915,i965"
DRIDRIVERS_append_x86-64 = ",i915,i965"

DEPENDS += "libxt "

EXTRA_OECONF = "--with-driver=dri --disable-gallium --without-gallium-drivers --with-dri-drivers=${DRIDRIVERS}"

EXTRA_OECONF += "--enable-gles1 --enable-gles2 --with-egl-platforms='drm' --enable-xcb --enable-gbm --enable-shared-glapi --enable-glx-tls"

