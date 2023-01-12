#!/bin/bash
while :
do
	read -p "Enter the file_name to get top 3 repeatative words and 3 longest line " FILE
	
	if [[ -f "$FILE" ]]
	then
		FILE_CONTENT=$(cat $FILE)
		break;
	else
		echo "File doesn't exists on the given path"
	fi
done

echo "Top 3 repeatative words are"
cat $FILE | tr '[:upper:]' '[:lower:]' | sed 's/[^[:alpha:][:space:]]//g' | tr ' ' '\n' | sort | uniq -c | sort -nr | head -3

echo "Top 3 longest Lines"
cat $FILE | awk '{ print length, $0 }' | sort -nr | head -3