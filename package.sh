#!/bin/bash

BUILD=readernotes-0.3.0

echo "Building projecto structure"
mkdir $BUILD
mkdir $BUILD/usr
mkdir $BUILD/usr/bin
mkdir $BUILD/usr/bin/readernotes
mkdir $BUILD/usr/bin/readernotes/libs
mkdir $BUILD/usr/bin/readernotes/libs/jdom-2.0.6
mkdir $BUILD/usr/share
mkdir $BUILD/usr/share/applications
mkdir $BUILD/usr/share/pixmaps
mkdir $BUILD/DEBIAN
mv target/$BUILD.jar $BUILD/usr/bin/readernotes/
cp src/main/resources/readernotes.desktop $BUILD/usr/share/applications/
cp src/main/resources/readernotes_logo.png $BUILD/usr/share/pixmaps/
cp src/main/resources/control $BUILD/DEBIAN/
dpkg-deb --build $BUILD
rm -r $BUILD
