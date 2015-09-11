echo "Downloading (but not installing!) required 3rd-party software: downloading SVN client and 7-Zip to extract it."
md svn
cd svn
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/7zip/7za.exe -OutFile 7za.exe}"
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/svn/svn.tar.lzma -OutFile svn.tar.lzma}"
7za x svn.tar.lzma
7za x svn.tar
cd.. 

echo "Downloading experimental results via 'svn-export' from GitHub."
svn\svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/results

echo "Downloading evaluation/configuration via 'svn export' from GitHub."
svn\svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/maxSat/evaluation

rd /s /q svn
echo "Done."