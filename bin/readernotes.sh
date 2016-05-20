#!/bin/bash

read option

if [ "$option" == "run" ]; then
	java -jar /usr/bin/readernotes/bin/readernotes.jar
elif [ "$option" == "install" ]; then
	./usr/bin/readernotes/bin/install.sh
elif [ "$option" == "uninstall" ]; then
	./usr/bin/readernotes/bin/uninstall.sh
fi
