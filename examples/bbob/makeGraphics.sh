#!/bin/bash

currentDir=`pwd`

cd "../../shared/graphics/optimization/bbob/bbob_example_evaluation/"
outputDir=`pwd`

workingDir=`mktemp -d`
cd "${workingDir}"

${currentDir}/make.sh

cd "${workingDir}/reports/LaTeX/IEEEtran"
find . -name '*.pdf' -printf "'%p' '%h/IEEEtran_%f'\n" | xargs -n2 mv
cp -r ${workingDir}/reports/LaTeX/IEEEtran/graphics/* "${outputDir}"

currentOut="${outputDir}/bbob_example_evaluation_reports"
mkdir -p "${currentOut}"
gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=1 -dLastPage=1 -sOutputFile="${currentOut}/IEEEtran_report.pdf" "${workingDir}/reports/LaTeX/IEEEtran/IEEEtran_report.pdf"