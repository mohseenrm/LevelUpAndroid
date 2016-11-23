#!/bin/sh
git add -A

if [[ ! -z $1 ]]; then
	git commit -m $1
else
	git commit -m "No Meassage Provided"
fi

git push origin master