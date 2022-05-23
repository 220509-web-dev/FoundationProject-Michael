#!/bin/bash
fname=$1
Lname=$2
email=$3
username=$4
password=$5
echo "$1,$2,$3,$4,$5" >> data.csv

# usage
# sh registration.sh wezley singleton wezley.singleton@gmail.com wsingleton p4ssw0rd