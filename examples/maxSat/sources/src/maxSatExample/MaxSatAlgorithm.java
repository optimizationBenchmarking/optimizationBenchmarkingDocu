package maxSatExample;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is a base class for simple <a
 * href="http://en.wikipedia.org/wiki/Maximum_satisfiability_problem"
 * >MAX-SAT</a> solvers.
 */
public abstract class MaxSatAlgorithm implements Runnable {

  /** the benchmark folders */
  private static final String[] BENCHMARK_FOLDERS = {//
  "uf020", //$NON-NLS-1$
      "uf050", //$NON-NLS-1$
      "uf075", //$NON-NLS-1$
      "uf100", //$NON-NLS-1$
      "uf125", //$NON-NLS-1$
      "uf150", //$NON-NLS-1$      
      "uf100", //$NON-NLS-1$
      "uf175", //$NON-NLS-1$
      "uf200", //$NON-NLS-1$
      "uf225", //$NON-NLS-1$
      "uf250", //$NON-NLS-1$
  };

  /** create */
  public MaxSatAlgorithm() {
    super();
  }

  /**
   * Solve a MAX-3SAT problem instance
   *
   * @param f
   *          the objective function
   */
  protected abstract void solve(final MaxSatObjective f);

  /** Apply this algorithm to all benchmark instances */
  @Override
  public final void run() {
    final Path benchmarksPath, outputPath;
    final String name;
    String instanceName, runName;
    Path folderPath, instancePath, instanceRunsPath, runPath;
    int run, instance;

    name = this.toString();

    _print("Begin experiment with algorithm " + name);//$NON-NLS-1$

    benchmarksPath = Paths.get("..", "benchmark")//$NON-NLS-1$//$NON-NLS-2$
        .toAbsolutePath().normalize();
    outputPath = Paths.get("..", "results", name) //$NON-NLS-1$//$NON-NLS-2$
        .toAbsolutePath().normalize();

    try {
      for (final String folder : MaxSatAlgorithm.BENCHMARK_FOLDERS) {
        folderPath = benchmarksPath.resolve(folder);
        _print("Now applying '" + name//$NON-NLS-1$
            + " to benchmarks of type '" + //$NON-NLS-1$
            folder + '\'');

        for (instance = 1; instance <= 10; instance++) {
          instanceName = (folder + '-');
          if (instance < 10) {
            instanceName = ((instanceName + '0') + ((char) ('0' + instance)));
          } else {
            instanceName += instance;
          }
          _print(name + " now working on instance '" + //$NON-NLS-1$
              instanceName + '\'');
          instancePath = folderPath.resolve(instanceName + ".cnf");//$NON-NLS-1$
          instanceRunsPath = outputPath.resolve(instanceName);
          for (run = 1; run <= 20; run++) {
            _print(name + " now begins run " + //$NON-NLS-1$
                run + " of instance '" + instanceName + '\'');//$NON-NLS-1$

            runName = String.valueOf(run);
            if (runName.length() < 2) {
              runName = ('0' + runName);
            }
            runPath = instanceRunsPath.resolve(((((//
                instanceName) + '_')
                + name + '_') + runName) + ".txt");//$NON-NLS-1$
            try (final MaxSatObjective f = new MaxSatObjective(
                instancePath, runPath)) {
              this.solve(f);
            }
          }
        }
      }
    } catch (final Throwable error) {
      _printStackTrace(error);
    } finally {
      _print("Finished experiment with algorithm " + name);//$NON-NLS-1$
    }
  }

  /**
   * Print a given string
   * 
   * @param string
   *          the string
   */
  static final void _print(final String string) {
    synchronized (System.out) {
      synchronized (System.err) {
        System.out.println(string);
      }
    }
  }

  /**
   * Print a stack trace
   * 
   * @param error
   *          the error
   */
  static final void _printStackTrace(final Throwable error) {
    synchronized (System.out) {
      synchronized (System.err) {
        error.printStackTrace(System.err);
      }
    }

  }
}
