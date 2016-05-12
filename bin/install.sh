#!/bin/bash

echo "Preparing files"
mkdir ../../readernotes
cp -r ../libs ../../readernotes
cp -r ../bin ../../readernotes
cp ../LICENSE ../../readernotes
cp ../readernotes.jar ../../readernotes
echo "Installing application"
sudo cp ../resources/readernotes_logo.png /usr/share/pixmaps
sudo cp ../resources/readernotes.desktop /usr/share/applications
sudo mv -r ../../readernotes /usr/bin
source /usr/share/applications/readernotes.desktop
echo "Installation complete, please enjoy Reader Notes, any bugs you
find feel free to report them at http://github.com/Morierti/ReaderNotes.
Thank You."



