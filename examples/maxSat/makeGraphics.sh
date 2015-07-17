#!/bin/bash

currentDir=`pwd`

cd "../../shared/graphics/optimization/sat/max3sat_example_evaluation/"
outputDir=`pwd`

workingDir=`mktemp -d`
cd "${workingDir}"

${currentDir}/make.sh

currentOut="${outputDir}/max3sat_example_evaluation_reports"
mkdir -p "${currentOut}"
gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=1 -dLastPage=1 -sOutputFile="${currentOut}/IEEEtran_report.pdf" "${workingDir}/reports/LaTeX/IEEEtran/report.pdf"
gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=1 -dLastPage=1 -sOutputFile="${currentOut}/LNCS_report.pdf" "${workingDir}/reports/LaTeX/LNCS/report.pdf"
gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=1 -dLastPage=1 -sOutputFile="${currentOut}/SigAlternate_report.pdf" "${workingDir}/reports/LaTeX/SigAlternate/report.pdf"

wkhtmltopdf -s A4 "${workingDir}/reports/XHTML/report.xhtml" "${workingDir}/reports/XHTML/report.pdf"
gs -sDEVICE=pdfwrite -dNOPAUSE -dBATCH -dSAFER -dFirstPage=1 -dLastPage=1 -sOutputFile="${currentOut}/XHTML_report.pdf" "${workingDir}/reports/XHTML/report.pdf"

currentOut="${outputDir}/ECDF_log_10_FEs_F_k_0"
mkdir -p "${currentOut}"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/ECDF_log_10_FEs_F_k_0.pdf" "${currentOut}/IEEEtran_ECDF_log_10_FEs_F_k_0.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/ECDF_log_10_FEs_F_k_0.pdf" "${currentOut}/LNCS_ECDF_log_10_FEs_F_k_0.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_FEs_F_k_0.pdf" "${currentOut}/SigAlternate_ECDF_log_10_FEs_F_k_0.pdf"
cp "${workingDir}/reports/XHTML/graphics/ECDF_log_10_FEs_F_k_0.png" "${currentOut}/XHTML_ECDF_log_10_FEs_F_k_0.png"

currentOut="${outputDir}/med_med_log_10_FEs_n_F_distinct_k"
mkdir -p "${currentOut}"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_1065.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_1065.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_218.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_218.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_325.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_325.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_430.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_430.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_538.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_538.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_645.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_645.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_753.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_753.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_860.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_860.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_91.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_91.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_960.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_960.pdf"
cp "${workingDir}/reports/LaTeX/IEEEtran/graphics/med_med_log_10_FEs_n_F_distinct_k/med_med_log_10_FEs_n_F_distinct_k_legend.pdf" "${currentOut}/IEEEtran_med_med_log_10_FEs_n_F_distinct_k_legend.pdf"

currentOut="${outputDir}/ECDF_log_10_RT_F_k_0_01_distinct_n"
mkdir -p "${currentOut}"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_100.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_100.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_125.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_125.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_150.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_150.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_175.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_175.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_200.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_200.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_20.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_20.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_225.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_225.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_250.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_250.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_50.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_50.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_75.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_75.pdf"
cp "${workingDir}/reports/LaTeX/SigAlternate/graphics/ECDF_log_10_RT_F_k_0_01_distinct_n/ECDF_log_10_RT_F_k_0_01_distinct_n_legend.pdf" "${currentOut}/SigAlternate_ECDF_log_10_RT_F_k_0_01_distinct_n_legend.pdf"

currentOut="${outputDir}/med_stddev_log_10_RT_F_k_distinct_n"
mkdir -p "${currentOut}"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_100.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_100.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_125.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_125.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_150.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_150.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_175.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_175.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_200.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_200.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_20.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_20.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_225.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_225.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_250.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_250.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_50.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_50.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_75.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_75.pdf"
cp "${workingDir}/reports/LaTeX/LNCS/graphics/med_stddev_log_10_RT_F_k_distinct_n/med_stddev_log_10_RT_F_k_distinct_n_legend.pdf" "${currentOut}/LNCS_med_stddev_log_10_RT_F_k_distinct_n_legend.pdf"