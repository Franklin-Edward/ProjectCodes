#!/bin/bash

read -p "Enter a String to check its occurance: " SEARCH

while :
do
	read -p "Enter the directory path to check occurance: " MY_DIR_PATH
	if [[ -d "$MY_DIR_PATH" ]]
	then
		NUM_OF_FILES=$(ls "$MY_DIR_PATH" | wc -l)
		break
	else
		echo "Invalid directory path"
	fi
done
echo "The String $SEARCH in each file:"
for FILE in $MY_DIR_PATH/*
do
	WORDS=$(cat $FILE | tr '[:upper:]' '[:lower:]')
	WORD_COUNT=$(echo "$WORDS" | grep -o "$SEARCH" | wc -l)
	echo "$(basename $FILE) : $WORD_COUNT"
done
