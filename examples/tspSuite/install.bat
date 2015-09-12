echo "Downloading (but not installing!) required 3rd-party software: downloading SVN client and 7-Zip to extract it."
md svn
cd svn
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/7zip/7za.exe -OutFile 7za.exe}"
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/svn/svn.tar.lzma -OutFile svn.tar.lzma}"
7za x svn.tar.lzma
7za x svn.tar
cd.. 

echo "Downloading experimental results and configuration via 'svn-export' from GitHub."
svn\svn export --force https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/tspSuite .

svn\7za x results.tar.lzma
del results.tar.lzma
svn\7za x results.tar -oresults
del results.tar

rd /s /q svn

del *.sh
del *.bat
del README.md
echo "Done."