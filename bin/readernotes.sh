#!/bin/bash

cd /home/$USER/
if [ -d ".readernotes" ]; then
	echo "Directory exists."
	java -jar /usr/bin/readernotes/readernotes.jar
else
	mkdir /home/$USER/.readernotes
	java -jar /usr/bin/readernotes/readernotes.jar
fi

