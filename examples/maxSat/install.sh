outputDir=`pwd`
echo "Writing output to folder '${outputDir}'"

echo "Downloading experimental results via 'svn export' from GitHub."
svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/results
echo "Downloading evaluation/configuration via 'svn export' from GitHub."
svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/evaluation

echo "Done."