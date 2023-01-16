#!/bin/bash

DATE=$(date +%Y-%m-%dT%T)
INPUT_FILE_PATH="./readinputs/"

CUTOFF_DATE="01-Jan-2023"
fixed_bonus=10000

FILE=$(cat "$INPUT_FILE_PATH"input.txt | sed '1d')

IFS=$'\n'
for line in ${FILE[@]}
do
	NAME=$(echo $line | awk '{print $2}')
	EMP_TYPE=$(echo $line | awk '{print $3}')
	joining_date=$(echo $line | awk '{print $4}')
	status=$(echo $line | awk '{print $5}')

	diff=$(echo $(($(date -jf "%d-%b-%Y" $CUTOFF_DATE +%s) - $(date -jf "%d-%b-%Y" $joining_date +%s))))

	DIFF_YEARS=$(expr $diff / 31536000)

	if [[ $DIFF_YEARS  -lt 1 ]] && [[ $status == "Active" ]]
	then
		DIFF_MONTHS=$(expr $diff / 2592000)
		BONUS=$(echo "$fixed_bonus*$DIFF_MONTHS/12" | bc)
	elif [[ $DIFF_YEARS -ge 1 ]]
	then
		BONUS=$(echo "$fixed_bonus*$DIFF_YEARS" | bc)
	else
		BONUS=0
	fi

  if [[ $status == "InActive" ]]
  then
  	BONUS=0
  fi

  if [[ $BONUS -eq 0 ]]
  then
  	echo "$NAME joined as $emp_type employee, joined on $joining_date. No Bonus" >> output-"$DATE".bonus
  else
  	echo "$NAME joined as $emp_type employee, joined on $joining_date. Bonus has to be paid Rs $BONUS" >> output-"$DATE".bonus
  fi
done 