#!/bin/bash

echo "Enter a number to check if its prime or not:"
read NUMBER

if [[ "$NUMBER" = 1 ]]
then
	echo "$NUMBER is not a prime number"
	exit "0"
elif [[ "NUMBER" -lt 4 ]]
then
	echo "$NUMBER is a prime number"
	exit "0"
elif [[ $((NUMBER % 2)) -eq 0 || $((NUMBER % 3)) -eq 0 ]]
then
	echo "$NUMBER is not a prime number"
	exit "0"
fi

for((i=5;(i * i)<=$NUMBER;++i))
do
	if [[ $((NUMBER % i)) -eq 0 || $((NUMBER %(i+2))) -eq 0 ]]
	then
		echo "$NUMBER is not a prime number"
		exit "0"
	fi
done

echo "$NUMBER is a prime number"