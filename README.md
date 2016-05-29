# Reader Notes
<hr>

Reader Notes allows you to take notes of books you are reading and heaving
all the books and reading files organized in one place.

##Instalation:
<hr>

If you are in a debian based system, download the deb package and run

dpkg -i readernotes-0.2.5.deb

in case you are not in a debian based system you have to download the source
code and compile it.

###Step 1:

Download the source code

###Step 2:

Compite it in the root directory of the project with:

make
make package-jar

###Step 3: 

Create folder in the root directory and copy the .jar file and the libs package
to that new folder, example:

mkdir readernotes
mv readernotes-0.2.5 readernotes
cp libs readernotes

###Step4:

Copy the newly created folder /usr/bin 

###Step 5:

Copy the .desktop file from the resources folder to /usr/share/applications
and the .png file to /usr/share/pixmaps and you are all set.

(Note: You will need super user permissions for steps 4 and 5).


To run the program run

java -jar readernotes-0.2.5.jar

##Dependencies
<hr>

All dependencies are releases along with the source code and binary packages.

jdom-2.0.6
java-hamcrest-2.0.0.0
junit-4.12
