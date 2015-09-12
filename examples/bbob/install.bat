echo "Downloading (but not installing!) required 3rd-party software: downloading SVN client and 7-Zip to extract it."
md svn
cd svn
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/7zip/7za.exe -OutFile 7za.exe}"
powershell -command "& {iwr https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/raw/master/tools/windows/svn/svn.tar.lzma -OutFile svn.tar.lzma}"
7za x svn.tar.lzma
7za x svn.tar
cd.. 

echo "Downloading experimental results from http://coco.lri.fr/BBOB2013/rawdata/
md results
cd results
for %i in (hutter2013_CMAES.tar liao2013_IPOP.tar liao2013_IPOP-500.tar liao2013_IPOP-tany.tar ^
liao2013_IPOP-texp.tar tran2013_P-DCN.tar pal2013_DE.tar  pal2013_fmincon.tar ^
pal2013_simplex.tar pal2013_HMLSL.tar holtschulte2013_hill.tar holtschulte2013_ga100.tar) do ^
powershell -command "& { iwr http://coco.lri.fr/BBOB2013/rawdata/%i.gz -OutFile %i.gz }" && ^
..\svn\7za x %i.gz && ^
..\svn\7za x %i && ^
del %i.gz && ^
del %i

cd ..

echo "Downloading evaluation/configuration via 'svn export' from GitHub."
svn\svn export https://github.com/optimizationBenchmarking/optimizationBenchmarkingDocu/branches/master/examples/bbob/evaluation

rd /s /q svn

echo "Done."