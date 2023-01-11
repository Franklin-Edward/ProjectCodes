#!/bin/bash

while :
do
  read -p "Enter no of elements to be printed in fibonnaci serie: (n>0) " N
  if [[ "$N" =~ ^[0-9]+$  && $N -ne 0 ]]; then
    break
  else
    echo "Invalid input, please try again."
  fi
done

if [[ "$N" -eq 1 ]]
then
	echo "The Fibonnaci series is : 0"
	exit "0"
fi

if [[ "$N" -eq 2 ]]; then
	echo "The Fibonnaci series is : 0 1"
	exit "0"
fi

NUMBER1=0
NUMBER2=1
echo "The Fibonnaci series is :"
echo "0"
echo "1"
for ((i=3;i<=N;++i)); do
	NUMBER2=$((NUMBER1+NUMBER2))
	NUMBER1=$((NUMBER2-NUMBER1))
	echo "$NUMBER2"
done