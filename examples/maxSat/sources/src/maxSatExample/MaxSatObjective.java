package maxSatExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

/**
 * This class implements the objective function for a <a
 * href="http://en.wikipedia.org/wiki/Maximum_satisfiability_problem"
 * >MAX-SAT</a> problem. It fulfills the following tasks:
 * <ol>
 * <li>Load a benchmark instance from a file.</li>
 * <li>Provide basic information about the benchmark instance to the
 * solver.</li>
 * <li>Evaluate a candidate solution and return its quality.</li>
 * <li>Collect all log data and store the log data into a file.</li>
 * </ol>
 */
public class MaxSatObjective implements Closeable {

  /** split a string into a string array by spaces */
  private static final Pattern SPLIT_BY_SPACE = Pattern.compile("\\s+");//$NON-NLS-1$

  /** the maximum number of FEs: {@value} */
  private static final int MAX_FES = 20_000_000;

  /** the clauses */
  private final int[][] m_clauses;

  /** the number of variables */
  private final int m_varCount;

  /** the output path */
  private final BufferedWriter m_output;

  /** the pre-allocated array of log points */
  private final __LogPoint[] m_logPoints;

  /** the start time */
  private final long m_startNanoTime;

  /** the number of used log points */
  private int m_logPointCount;

  /** the number of ellapsed FEs */
  private int m_FEs;

  /** the best objective value */
  private int m_best;

  /** was the optimum found? */
  private boolean m_foundOptimum;

  /**
   * Create the MAX-SAT objective function by supplying paths to the
   * benchmark instance data source and to the output destination file.
   *
   * @param benchmark
   *          the benchmark instance source
   * @param output
   *          the output destination
   * @throws IOException
   *           if I/O fails
   */
  public MaxSatObjective(final Path benchmark, final Path output)
      throws IOException {
    super();

    final Charset charset;
    int[][] data;
    int[] currentVars;
    int maxVar;
    String[] split;
    String line;
    int clauseIndex, varIndex, var, pointIndex;

    charset = Charset.forName("UTF-8"); //$NON-NLS-1$
    data = null;
    maxVar = clauseIndex = 0;

    // First, we load the problem definition into a n*3 matrix, where n is
    // the number of clauses.
    try (final BufferedReader reader = Files.newBufferedReader(benchmark,
        charset)) {

      loop: while ((line = reader.readLine()) != null) {
        line = line.trim().toLowerCase();
        if (line.length() <= 0) {
          continue loop;
        }

        switch (line.charAt(0)) {
          case 'c': {
            continue loop;
          }
          case 'p': {
            if (data != null) {
              throw new IllegalArgumentException("Line '" //$NON-NLS-1$
                  + line + //
                  "' not expected here, we have already read the problem size.");//$NON-NLS-1$
            }

            split = MaxSatObjective.SPLIT_BY_SPACE.split(line);
            if (split.length != 4) {
              throw new IllegalArgumentException("Line '" //$NON-NLS-1$
                  + line + //
                  "' should have consist of four space-separated elements.");//$NON-NLS-1$
            }
            data = new int[Integer.parseInt(split[3])][];
            maxVar = Integer.parseInt(split[2]);
            continue loop;
          }
          case '%':
          case '0': {
            break loop;
          }

          default: {
            if (data != null) {
              split = MaxSatObjective.SPLIT_BY_SPACE.split(line);
              if (split.length <= 0) {
                throw new IllegalArgumentException("Line '" //$NON-NLS-1$
                    + line + //
                    "' should have at least two space-separated elements.");//$NON-NLS-1$
              }

              if (clauseIndex >= data.length) {
                throw new IllegalArgumentException("Only "//$NON-NLS-1$
                    + data.length + //
                    " clauses expected, but more encountered.");//$NON-NLS-1$
              }
              data[clauseIndex] = currentVars = new int[split.length - 1];
              varLooper: for (varIndex = 0; varIndex < split.length; varIndex++) {
                var = Integer.parseInt(split[varIndex]);
                if (var == 0) {
                  if (varIndex != (split.length - 1)) {
                    throw new IllegalArgumentException(//
                        "0 variable can only occur at end of line '" + line + '\'');//$NON-NLS-1$
                  }
                  break varLooper;
                }
                if (Math.abs(var) > maxVar) {
                  throw new IllegalArgumentException(//
                      "Illegal variable index " + var + //$NON-NLS-1$
                          " in '" + line + '\'');//$NON-NLS-1$
                }
                currentVars[varIndex] = var;
              }
              clauseIndex++;
              continue loop;
            }
            throw new IllegalArgumentException(//
                "Here should be the problem size definition, but we found '" //$NON-NLS-1$
                    + line + '\'');
          }
        }
      }
    }

    if (data == null) {
      throw new IllegalArgumentException(
          "No problem size definition found!");//$NON-NLS-1$
    }
    if (clauseIndex != data.length) {
      throw new IllegalArgumentException(data.length
          + " clauses expected, but " + clauseIndex + //$NON-NLS-1$
          " found.");//$NON-NLS-1$
    }

    // OK, all clauses have been read.
    this.m_clauses = data;
    this.m_varCount = maxVar;

    // There can be at most data.length+1 different objective values, plus
    // one last log point
    this.m_logPoints = new __LogPoint[data.length + 2];
    for (pointIndex = this.m_logPoints.length; (--pointIndex) >= 0;) {
      this.m_logPoints[pointIndex] = new __LogPoint();
    }

    // Create the output directory structure (if necessary) and writer.
    Files.createDirectories(output.getParent());
    try {
      Files.deleteIfExists(output); // just in case
    } catch (Throwable error) {
      // ignore
    }
    this.m_output = Files.newBufferedWriter(output, charset,
        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    // Anything will be better than this...
    this.m_best = Integer.MAX_VALUE;

    MaxSatAlgorithm._print("Begin run for input '" + benchmark + //$NON-NLS-1$
        "' and output '" + output + '\'');//$NON-NLS-1$

    // After this call returns, the optimization begins
    this.m_startNanoTime = System.nanoTime();
  }

  /**
   * Obtain the number of clauses
   *
   * @return the number of clauses
   */
  public final int getNumberOfClauses() {
    return this.m_clauses.length;
  }

  /**
   * Get the number of variables
   *
   * @return the number of variables
   */
  public final int getNumberOfVariables() {
    return this.m_varCount;
  }

  /**
   * Obtain the number of unsatisfied clauses in a given solution. A return
   * value of {@code 0} means we have found the optimum. Any value from
   * <code>0..{@link #getNumberOfClauses()}</code> is possible, where
   * {@link #getNumberOfClauses()} denotes the worst possible solution,
   * which leaves all clauses unsatisfied.
   *
   * @param solution
   *          the solution
   * @return the objective value, subject to minimization
   */
  public final int evaluate(final boolean[] solution) {
    final __LogPoint point;
    int falseClauses;

    // Find the number of false clauses.
    falseClauses = this.m_clauses.length;
    looper: for (final int[] clause : this.m_clauses) {
      for (final int var : clause) {
        if ((var < 0) ? (!solution[(-1) - var]) : solution[var - 1]) {
          falseClauses--;
          continue looper;
        }
      }
    }

    this.m_FEs++;

    // Check if we have an improvement.
    if (falseClauses < this.m_best) {
      if (falseClauses <= 0) {
        this.m_foundOptimum = true;
      }
      // Store a log point in the cache.
      this.m_best = falseClauses;
      point = this.m_logPoints[this.m_logPointCount++];
      point.m_FEs = this.m_FEs;
      point.m_unsatisfied = falseClauses;
      point.m_nanoTime = (System.nanoTime() - this.m_startNanoTime);
    }

    // Return the objective value.
    return falseClauses;
  }

  /**
   * The termination criterion: Either {@value #MAX_FES} function
   * evaluations (calls to {@link #evaluate(boolean[])}) have been
   * performed or the optimum was found (in which case we do not search any
   * further).
   *
   * @return {@code true} if the solver should stop, {@code false} if it
   *         can continue
   */
  public final boolean shouldTerminate() {
    return ((this.m_FEs >= MaxSatObjective.MAX_FES) || this.m_foundOptimum);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    final long totalTime;
    final __LogPoint[] points;
    __LogPoint point;
    int index, count;

    totalTime = (System.nanoTime() - this.m_startNanoTime);
    count = 0;
    try {
      try {
        count = this.m_logPointCount;
        points = this.m_logPoints;
        // Check if we should take a final log point to signify the end of
        // this run.
        if ((!this.m_foundOptimum) && ((count <= 0)//
            || ((point = points[count - 1]).m_FEs != this.m_FEs)//
        || (point.m_nanoTime != totalTime))) {
          point = points[count++];
          point.m_FEs = this.m_FEs;
          point.m_unsatisfied = Math.min(this.m_clauses.length,
              this.m_best);
          point.m_nanoTime = totalTime;
        }

        // Write all cached log points.
        for (index = 0; index < count; index++) {
          point = points[index];
          this.m_output.write(//
              String.valueOf(point.m_FEs) + '\t' + //
                  String.valueOf(point.m_nanoTime) + '\t' + //
                  String.valueOf(point.m_unsatisfied));
          this.m_output.newLine();
        }
      } finally {
        this.m_output.close();
      }
    } finally {
      MaxSatAlgorithm._print("Finished run. Collected " + count + //$NON-NLS-1$
          " log points and found best solution " + this.m_best);//$NON-NLS-1$
    }
  }

  /** a log point */
  private static final class __LogPoint {
    /** the function evaluation */
    int m_FEs;

    /** the number of nano seconds that have passed */
    long m_nanoTime;

    /** the number of unsatisfied clauses */
    int m_unsatisfied;

    /** create */
    __LogPoint() {
      super();
    }
  }
}
