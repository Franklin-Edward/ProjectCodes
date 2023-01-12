#!/bin/bash
echo "Generating 1 to 30 squares and storing in a txt file"
SQUARES="1=1"
for((i=2;i<=30;++i))
do
	SQUARES+=",${i}=$((i*i))"
done
echo "$SQUARES" > "squares.txt"

if [[ ${?} -eq 0 ]]
then
	echo "File named squares.txt generated successfully"
else
	echo "File named squares.txt is not generated successfully"
	echo "Generation failed with exit code ${?}"
	exit ${?}
fi

READ_FROM_FILE=$(cat squares.txt)
IFS=','
SQS=($READ_FROM_FILE)
IFS='='
for SQ in "${SQS[@]}"
do
	TEMP=($SQ)
	echo "Square of ${TEMP[0]} is ${TEMP[1]}"
done
exit "0"