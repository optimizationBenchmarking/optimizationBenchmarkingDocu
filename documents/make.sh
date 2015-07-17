#!/bin/bash

sourceDir=`pwd`

echo "Building all examples and documents."

echo "Now building examples."
cd ../examples/
./makeGraphics.sh

echo "Now building documents."
cd ${sourceDir}
cd ./evaluatorSlides
./make.sh

cd ${sourceDir}

echo "Done building all examples and documents."