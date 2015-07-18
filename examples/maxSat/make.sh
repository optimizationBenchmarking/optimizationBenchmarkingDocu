#!/bin/bash

jarName="optimizationBenchmarking-full.jar"

outputDir=`pwd`
echo "Writing output to folder '${outputDir}'"

echo "Downloading experimental results via 'svn export' from GitHub."
svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/results
echo "Downloading evaluation/configuration via 'svn export' from GitHub."
svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/evaluation

jarDownloadURL=$(wget "http://optimizationbenchmarking.github.io/optimizationBenchmarking/currentVersion.url" -q -O -)
echo "Downloading evaluator from '${jarDownloadURL}'."
wget -O "${outputDir}/${jarName}" "${jarDownloadURL}"

echo "Applying evaluator and obtaining reports in different formats."
cd "${outputDir}/evaluation"
java -jar "${outputDir}/${jarName}" -configXML=configForIEEEtran.xml
java -jar "${outputDir}/${jarName}" -configXML=configForLNCS.xml
java -jar "${outputDir}/${jarName}" -configXML=configForSigAlternate.xml
java -jar "${outputDir}/${jarName}" -configXML=configForXHTML.xml
java -jar "${outputDir}/${jarName}" -configXML=configForExport.xml

cd "${outputDir}"
echo "Done."