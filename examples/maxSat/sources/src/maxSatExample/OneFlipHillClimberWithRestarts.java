package maxSatExample;

import java.util.Random;

/**
 * <p>
 * A simple Hill Climbing algorithm which tries to flip a single bit in
 * each iteration and accepts the new solution if it is better.
 * </p>
 * <p>
 * This algorithm will re-start if it did not find any solution equally
 * good or better than the optimum for {@code z} steps. {@code z} is
 * initialized to {@code 1} and increased by one at every restart.
 * </p>
 */
public final class OneFlipHillClimberWithRestarts extends MaxSatAlgorithm {

  /** create */
  public OneFlipHillClimberWithRestarts() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final void solve(final MaxSatObjective f) {
    final boolean[] solution;
    final Random rand;
    final int n;
    int index;
    int currentUnsatisfied, bestUnsatisfied, nextRestart, withoutImprovementOrEqual;

    rand = new Random();
    n = f.getNumberOfVariables();

    solution = new boolean[n];
    nextRestart = 0;
    while (!(f.shouldTerminate())) {
      nextRestart++; // increase restart counter
      withoutImprovementOrEqual = 0; // reset no-improvement counter

      // create the first, random solution
      for (index = 0; index < n; index++) {
        solution[index] = rand.nextBoolean();
      }
      bestUnsatisfied = f.evaluate(solution);// and evaluate it

      inner: while (!(f.shouldTerminate())) {

        while (!(f.shouldTerminate())) {
          index = rand.nextInt(n); // choose a bit to flip
          solution[index] ^= true; // flip the bit
          currentUnsatisfied = f.evaluate(solution); // check new solution
          if (currentUnsatisfied > bestUnsatisfied) { // if it is worse...
            solution[index] ^= true; // ...flip bit again = undo

            if ((++withoutImprovementOrEqual) >= nextRestart) {
              break inner; // we need to do a restart
            }
          } else { // otherwise, remember objective value
            bestUnsatisfied = currentUnsatisfied;
            withoutImprovementOrEqual = 0; // reset no-improvement counter
          }
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "1FlipHCrs"; //$NON-NLS-1$
  }

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   */
  public static final void main(final String[] args) {
    new OneFlipHillClimberWithRestarts().run();
  }
}
