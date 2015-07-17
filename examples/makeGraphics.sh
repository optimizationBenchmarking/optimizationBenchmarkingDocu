#!/bin/bash

echo "Begin making all graphics."

examplesDir=`pwd`

echo "Begin making 'bbob' graphics."
cd ${examplesDir}
cd ./bbob/
./makeGraphics.sh
echo "Done making 'bbob' graphics."

echo "Begin making 'maxSat' graphics."
cd ${examplesDir}
cd ./maxSat/
./makeGraphics.sh
echo "Done making 'maxSat' graphics."

echo "Begin making 'tspSuite' graphics."
cd ${examplesDir}
cd ./tspSuite/
./makeGraphics.sh
echo "Done making 'tspSuite' graphics."

echo "Done making all graphics."