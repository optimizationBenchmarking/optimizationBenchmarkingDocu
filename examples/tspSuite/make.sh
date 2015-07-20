#!/bin/bash

jarName="optimizationBenchmarking-full.jar"

outputDir=`pwd`
echo "Writing output to folder '${outputDir}'"

echo "Downloading experimental results and evaluation configuration via 'svn export' from GitHub."
svn export --force https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/tspSuite "${outputDir}"

echo "Unpacking experimental results."
mkdir -p "${outputDir}/results"
cd "${outputDir}/results"
tar -xvf "${outputDir}/results.tar.lzma"
rm "${outputDir}/results.tar.lzma"

jarDownloadURL=$(wget "http://optimizationbenchmarking.github.io/optimizationBenchmarking/currentVersion.url" -q -O -)
echo "Downloading evaluator from '${jarDownloadURL}'."
wget -O "${outputDir}/${jarName}" "${jarDownloadURL}"

echo "Applying evaluator and obtaining report."
cd "${outputDir}/evaluation"
java -jar "${outputDir}/${jarName}" -configXML=configForSigAlternate.xml

cd "${outputDir}"
rm make.sh
rm make.bat
rm makeGraphics.sh
echo "Done."