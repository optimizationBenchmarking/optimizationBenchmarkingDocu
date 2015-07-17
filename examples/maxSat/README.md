# The MAX-3SAT Example

In this folder, we provide an example application of the *optimizationBenchmarking.org* evaluator. We use it to investigate the results of some simple algorithms applied to the [MAX-3SAT](http://en.wikipedia.org/wiki/MAX-3SAT) problem.

## Example Structure

This example is structured as follows

1. The folder `benchmark` contains some benchmark instances for the MAX-3SAT problem from a popular benchmark instance set. A detailed description of these instances is given in the `README.md` file inside the folder.
2. `make.sh`, a bash shell script which can be used to download all files needed for the example (such as the evaluator component and all results) and create the example reports live on your machine!
3. The folder `sources` contains some simple `Java` "solvers" to solve these MAX-3SAT instances. These "solvers" (notice the quotes) are not an attempt to actually solve the problems well - they are quite stupid and primitive. But they are easy to understand, which is the goal of the example. They also illustrate how data can be gathered in log files which can later be loaded into the evaluator.
4. The folder `results` contains the results of these algorithms.
5. The folder `evaluation` contains the configuration files to generate some evaluation reports with our evaluator component. 

## The MAX-3SAT Problem

[MAX-3SAT](http://en.wikipedia.org/wiki/MAX-3SAT) is an optimization problem where we want to find a way to satisfy a Boolean function of a specific structure. "Satisfy" means to find a setting for its (Boolean) input values so that its (Boolean) output becomes `true`. If there is no such setting, well, at least we want to make as many of its components ("clauses") to become `true`. The Boolean functions considered here are in [conjunctive normal form](http://en.wikipedia.org/wiki/3-CNF), meaning they are an `and` combination of several smaller `or`s. Moreover, each of these `or`s has exactly three inputs, which come from the inputs or negated inputs of the overall formula. In particular, such a formula `f(x1, x2, x3, x4)` could look like

    f(x1, x2, x3, x4) = (x1 or x2 or (not x3)) and (x2 or (not x3) or x4) and ((not x1) or x2 or (not x4))

This formula has four variables (`x1`, `x2`, `x3`, and `x4`) and three clauses. If can become `true` if
`x2` is `true` (as it appears in each `or` without negation), in which case the other variables don't matter. For problems more variables and clauses, it becomes much harder to find a solution or to find the variable setting making the most clauses become `true`. Matter of fact, MAX-3SAT is [NP-hard](http://en.wikipedia.org/wiki/NP-hard).

As benchmark problems for our Maximum 3 Satisfiability example, we use some instances from the well-known [SATLib](http://www.satlib.org/) obtained from [http://www.cs.ubc.ca/~hoos/SATLIB/benchm.html](http://www.cs.ubc.ca/~hoos/SATLIB/benchm.html) [1], as we describe in the `README.md` file in the `benchmark` folder.

## References
1. Holger H. Hoos and Thomas Stützle: SATLIB: An Online Resource for Research on SAT. In: Ian P.Gent, Hans van Maaren, and Tobi Walsh, editors, SAT 2000, pp.283-292, Amsterdam, The Netherlands: IOS Press, 2000. SATLIB is available online at [www.satlib.org](http://www.satlib.org/).