#!/bin/bash

jarName="optimizationBenchmarking-full.jar"
bbobDownloadBaseURL="http://coco.lri.fr/BBOB2013/rawdata"

outputDir=`pwd`
echo "Writing output to folder '${outputDir}'"

echo "Downloading selected experimental results from '${bbobDownloadBaseURL}'."
mkdir -p "${outputDir}/results"
cd "${outputDir}/results"
for archive in "hutter2013_CMAES.tar.gz" "liao2013_IPOP.tar.gz" "liao2013_IPOP-500.tar.gz" "liao2013_IPOP-tany.tar.gz" \
               "liao2013_IPOP-texp.tar.gz" "tran2013_P-DCN.tar.gz" "pal2013_DE.tar.gz"  "pal2013_fmincon.tar.gz" \
               "pal2013_simplex.tar.gz" "pal2013_HMLSL.tar.gz" "holtschulte2013_hill.tar.gz" "holtschulte2013_ga100.tar.gz"
do
  wget -O "${outputDir}/results/${archive}" "${bbobDownloadBaseURL}/$archive"
  tar -xvf "${outputDir}/results/${archive}"
  rm "${outputDir}/results/${archive}"
done

echo "Downloading evaluation/configuration via 'svn export' from GitHub."
cd "${outputDir}"
svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/bbob/evaluation

jarDownloadURL=$(wget "http://optimizationbenchmarking.github.io/optimizationBenchmarking/currentVersion.url" -q -O -)
echo "Downloading evaluator from '${jarDownloadURL}'."
wget -O "${outputDir}/${jarName}" "${jarDownloadURL}"

echo "Applying evaluator and obtaining report in IEEEtran format."
cd "${outputDir}/evaluation"
java -jar "${outputDir}/${jarName}" -configXML=configForIEEEtran.xml

cd "${outputDir}"
echo "Done."