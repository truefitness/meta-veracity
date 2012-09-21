
#Add local package repository to base-feed configuration file for opkg.

do_compile_append(){

    ipkgarchs="core2 all cedartrail"
    for arch in $ipkgarchs; do
            echo "src/gz $arch ${FEED_URI}$arch" >> $basefeedconf
    done

}

