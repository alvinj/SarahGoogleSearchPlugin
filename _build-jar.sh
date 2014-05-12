#!/bin/bash

sbt package

if [ $? != 0 ]
then
  echo "'sbt package' failed, exiting now"
  exit 1
fi

cp target/scala-2.10/googlesearch_2.10-0.1.jar GoogleSearch.jar

ls -l GoogleSearch.jar

echo ""
echo "Created GoogleSearch.jar. Copy that file to /Users/al/Sarah/plugins/DDGoogleSearch, like this:"
echo "cp GoogleSearch.jar /Users/al/Sarah/plugins/DDGoogleSearch"

