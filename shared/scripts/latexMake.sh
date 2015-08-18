#!/bin/bash

document=$1

echo "Cleaning up before making document $document.tex."

rm "$document.aux"
rm "$document.bbl"
rm "$document.blg"
rm "$document.dvi"
rm "$document.idx"
rm "$document.log"
rm "$document.nav"
rm "$document.out"
rm "$document.out.ps"
rm "$document.pdf"
rm "$document.ps"
rm "$document.snm"
rm "$document.toc"
rm "$document.synctex.gz"
rm "$document.tex.bak"
rm "$document.tex.sav"
rm "$document.tex~"
rm "$document.thm"
rm "$document.vrb"
rm "missfont.log"
rm "texput.log"

echo "Finished cleaning up before making document $document.tex"

echo "Now compiling $document.tex"

pdfsize=0
oldpdfsize=1
cycle=0
cycleCounter=0

while [ "$oldpdfsize" -ne "$pdfsize" ]; do
  cycle=$((cycle+1))
  echo "Now beginning build cycle $cycle."
  
  oldpdfsize=$pdfsize
  pdflatex "$document"
  pdflatex "$document"

  for i in *.aux; do
     bibtex "$i"
  done
  bibtex "$document"
 
  pdfsize=$(stat -c%s "$document.pdf")
  echo "Finished build cycle $cycle, size of $document.pdf is $pdfsize (was $oldpdfsize)."
  
  cycleCounter=$((cycleCounter+1))
  if [ "$cycleCounter" -ge 200 ]; then
    break
  fi
done

echo "Cleaning up after making document $document.pdf"

rm "$document.aux"
rm "$document.bbl"
rm "$document.blg"
rm "$document.dvi"
rm "$document.idx"
rm "$document.log"
rm "$document.nav"
rm "$document.out"
rm "$document.out.ps"
rm "$document.ps"
rm "$document.snm"
rm "$document.toc"
rm "$document.synctex.gz"
rm "$document.tex.bak"
rm "$document.tex.sav"
rm "$document.tex~"
rm "$document.thm"
rm "$document.vrb"
rm "missfont.log"
rm "texput.log"

echo "Finished cleaning up after making document $document.pdf"

if [ $# -gt 1 ]; then
  nextArgument="$2"
  if [ $nextArgument = "evince" ]; then
    evince "$document.pdf"
  fi
fi

echo "All done."
