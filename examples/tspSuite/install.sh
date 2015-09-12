outputDir=`pwd`
echo "Writing output to folder '${outputDir}'"

echo "Downloading experimental results and evaluation configuration via 'svn export' from GitHub."
svn export --force https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/tspSuite "${outputDir}"

echo "Unpacking experimental results."
mkdir -p "${outputDir}/results"
cd "${outputDir}/results"
tar -xvf "${outputDir}/results.tar.lzma"
rm "${outputDir}/results.tar.lzma"

cd "${outputDir}"
rm *.sh
rm *.bat
echo "Done."