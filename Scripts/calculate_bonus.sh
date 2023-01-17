#!/bin/bash

DATE=$(date +%Y-%m-%dT%T)
INPUT_FILE_PATH="./readinputs/"

CUTOFF_DATE="01-Jan-2023"
FIXED_BONUS=10000

FILE=$(cat "$INPUT_FILE_PATH"input.txt | sed '1d')

IFS=$'\n'
for line in ${FILE[@]}
do
	NAME=$(echo $line | awk '{print $2}')
	EMP_TYPE=$(echo $line | awk '{print $3}')
	JOINING_DATE=$(echo $line | awk '{print $4}')
	STATUS=$(echo $line | awk '{print $5}')

	DIFF=$(echo $(($(date -jf "%d-%b-%Y" $CUTOFF_DATE +%s) - $(date -jf "%d-%b-%Y" $JOINING_DATE +%s))))

	DIFF_YEARS=$(expr $DIFF / 31536000)

	if [[ $DIFF_YEARS  -lt 1 ]] && [[ $STATUS == "Active" ]]
	then
		DIFF_MONTHS=$(expr $DIFF / 2592000)
		BONUS=$(echo "$FIXED_BONUS*$DIFF_MONTHS/12" | bc)
	elif [[ $DIFF_YEARS -ge 1 ]]
	then
		BONUS=$(echo "$FIXED_BONUS*$DIFF_YEARS" | bc)
	else
		BONUS=0
	fi

  if [[ $STATUS == "InActive" ]]
  then
  	BONUS=0
  fi

  line="$NAME $EMP_TYPE $JOINING_DATE $STATUS $BONUS" 
  echo $line >> updatedinput.txt
done 

sort -k5,5nr updatedinput.txt > sort.txt``

awk '{if ($5 == 0) print $1 " is joined as " tolower($2) ", joined on " $3 ". No Bonus" > "output-'$DATE'.bonus"; else print $1 " is joined as " tolower($2) ", joined on " $3 ". Bonus has to be paid Rs." $5 > "output-'$DATE'.bonus"}' sort.txt

rm sort.txt
rm updatedinput.txt