#!/bin/bash
echo "Enter a value to check if it's a palindrome: "
read value
if [[ "$value" = $(echo $value | rev) ]]
then
  echo "$value is a palindrome."
else
  echo "$value is not a palindrome."
fi