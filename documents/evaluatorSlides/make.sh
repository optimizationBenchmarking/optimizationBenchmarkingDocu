#!/bin/bash

sourceDir=`pwd`

currentVersionURL=$(wget "http://optimizationbenchmarking.github.io/optimizationBenchmarking/currentVersion.url" -q -O -)
echo "current version url: ${currentVersionURL}"

currentVersion=`expr "${currentVersionURL}" :  '.*/\([0-9]*[.][0-9]*[.][0-9]*\)'`
echo "current version: ${currentVersion}"

currentExecutable=`expr "${currentVersionURL}" :  '.*/\([a-zA-Z]*-[0-9]*[.][0-9]*[.][0-9]*-[a-zA-Z]*.jar\)'`
echo "current executable: ${currentExecutable}"

echo "\xdef\optimizationBenchmarkingDownload{${currentVersionURL}}\xdef\optimizationBenchmarkingExecutable{${currentExecutable}}\xdef\optimizationBenchmarkingVersion{${currentVersion}}%" > version.tex

cd ${sourceDir}
../../shared/scripts/latexMake.sh evaluatorSlides 