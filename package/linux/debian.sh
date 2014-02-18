#!/bin/sh

PACKAGE_NAME="closurefxbuilder"
PACKAGE_VERSION="1.0.0"
SOURCE_DIR=$PWD
TEMP_DIR="/home/daginno/dist/linux"
SCRIPTDIRFP=`which $0`
SCRIPTDIR=`dirname $SCRIPTDIRFP`

rm -r $TEMP_DIR/debian/

mkdir -p $TEMP_DIR/debian/DEBIAN
mkdir -p $TEMP_DIR/debian/lib
mkdir -p $TEMP_DIR/debian/bin
mkdir -p $TEMP_DIR/debian/usr/share/applications
mkdir -p $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME

echo "Package: $PACKAGE_NAME" > $TEMP_DIR/debian/DEBIAN/control
echo "Version: $PACKAGE_VERSION" >> $TEMP_DIR/debian/DEBIAN/control
cat $SCRIPTDIR/control >> $TEMP_DIR/debian/DEBIAN/control

cp $SCRIPTDIR/closurefxbuilder.desktop $TEMP_DIR/debian/usr/share/applications/
cp $SCRIPTDIR/copyright $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/

cp $SCRIPTDIR/../../logo/logo-256.png $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/
chmod 644 $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/logo-256.png

cp -r $SCRIPTDIR/../../build/ $TEMP_DIR/debian/lib/$PACKAGE_NAME

gzip -9c $SCRIPTDIR/../../CHANGELOG > $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/changelog.gz

echo '#!/bin/sh' > $TEMP_DIR/debian/bin/closurefxbuilder
echo "java -cp \"/lib/$PACKAGE_NAME/*\" com.digiarea.closurefx.ClosureFX \"\$@\"" >> $TEMP_DIR/debian/bin/$PACKAGE_NAME
chmod 755 $TEMP_DIR/debian/bin/closurefxbuilder

PACKAGE_SIZE=`du -bs $TEMP_DIR/debian | cut -f 1`
PACKAGE_SIZE=$((PACKAGE_SIZE/1024))
echo "Installed-Size: $PACKAGE_SIZE" >> $TEMP_DIR/debian/DEBIAN/control

chown -R root $TEMP_DIR/debian/
chgrp -R root $TEMP_DIR/debian/

cd $TEMP_DIR/
dpkg --build debian
mv debian.deb $PACKAGE_NAME-$PACKAGE_VERSION.deb
#rm -r $TEMP_DIR/debian
