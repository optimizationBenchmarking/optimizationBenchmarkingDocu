# The TSP Suite Example

In this folder, we provide an example application of the *optimizationBenchmarking.org* evaluator. We use it to investigate some results obtained with
[TSP Suite](https://github.com/optimizationBenchmarking/tspSuite).

## Example Structure

This example is structured as follows

1. [`make.sh`](https://raw.githubusercontent.com/optimizationBenchmarking/optimizationBenchmarkingDocu/master/examples/tspSuite/make.sh), a bash shell script which can be used to download all files needed for the example (such as the evaluator component and all results) and create the example reports live on your machine!
2. [`make.bat`](https://raw.githubusercontent.com/optimizationBenchmarking/optimizationBenchmarkingDocu/master/examples/tspSuite/make.bat), a Windows command line script which can be used to download all files needed for the example (such as the evaluator component and all results) and create the example reports live on your machine!
3. The folder `evaluation` contains the configuration files to generate some evaluation reports with our evaluator component.
4. [`results.tar.lzma`](https://raw.githubusercontent.com/optimizationBenchmarking/optimizationBenchmarkingDocu/master/examples/tspSuite/results.tar.lzma) is an archive containing all the experimental results which are to be evaluated.

## References
1. Thomas Weise, Raymond Chiong, Ke Tang, Jörg Lässig, Shigeyoshi Tsutsui, Wenxiang Chen, Zbigniew Michalewicz, and Xin Yao. Benchmarking Optimization Algorithms: An Open Source Framework for the Traveling Salesman Problem. IEEE Computational Intelligence Magazine (CIM), 9(3):40-52, August 2014. doi:<a href="http://dx.doi.org/10.1109/MCI.2014.2326101">10.1109/MCI.2014.2326101</a>. <a href="http://www.it-weise.de/documents/files/WCTLTCMY2014BOAAOSFFTTSP.pdf">pdf</a>