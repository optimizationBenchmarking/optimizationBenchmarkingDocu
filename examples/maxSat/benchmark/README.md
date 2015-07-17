# Benchmark Problems for the MAX-3SAT Example

As benchmark problems for our Maximum 3 Satisfiability example, we use some instances from the well-known [SATLib](http://www.satlib.org/) obtained from [http://www.cs.ubc.ca/~hoos/SATLIB/benchm.html](http://www.cs.ubc.ca/~hoos/SATLIB/benchm.html) [1].

In particular, we pick the first ten instances of each of the following [uniform random 3-SAT](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/descr.html) benchmark sets:

1. [`uf20-91`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf20-91.tar.gz): 20 variables, 91 clauses - first 10 of 1000 instances, all satisfiable, all MAX 3-SAT
2. [`uf50-218`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf50-218.tar.gz): 50 variables, 218 clauses - first 10 of 1000 instances, all satisfiable, all MAX 3-SAT
3. [`uf75-325`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf75-325.tar.gz): 75 variables, 325 clauses - first 10 of 100 instances, all satisfiable
4. [`uf100-430`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf100-430.tar.gz): 75 variables, 325 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT
5. [`uf125-538`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf125-538.tar.gz): 100 variables, 430 clauses - first 10 of 1000 instances, all satisfiable, all MAX 3-SAT
6. [`uf150-645`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf150-645.tar.gz): 150 variables, 645 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT
7. [`uf175-753`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf175-753.tar.gz) 175 variables, 753 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT
8. [`uf200-860`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf200-860.tar.gz): 200 variables, 860 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT
9. [`uf225-960`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf225-960.tar.gz): 225 variables, 960 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT
10. [`uf250-1065`](http://www.cs.ubc.ca/~hoos/SATLIB/Benchmarks/SAT/RND3SAT/uf250-1065.tar.gz): 250 variables, 1065 clauses - first 10 of 100 instances, all satisfiable, all MAX 3-SAT

## Features
Our instances have two features:

1. The number of variables
2. The number of clauses (which here is a function of the number of variables)

## References
1. Holger H. Hoos and Thomas Stützle: SATLIB: An Online Resource for Research on SAT. In: Ian P.Gent, Hans van Maaren, and Tobi Walsh, editors, SAT 2000, pp.283-292, Amsterdam, The Netherlands: IOS Press, 2000. SATLIB is available online at [www.satlib.org](http://www.satlib.org/). 