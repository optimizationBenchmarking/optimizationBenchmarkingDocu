package maxSatExample;

import java.util.Random;

/**
 * <p>
 * A Hill Climbing algorithm which tries to flip one or two bits in each
 * iteration and accepts the new solution if it is better.
 * </p>
 * <p>
 * This algorithm will re-start if it did not find any solution equally
 * good or better than the optimum for {@code z} steps. {@code z} is
 * initialized to {@code 1} and increased by one at every restart.
 * </p>
 */
public final class TwoFlipHillClimberWithRestarts extends MaxSatAlgorithm {

  /** create */
  public TwoFlipHillClimberWithRestarts() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final void solve(final MaxSatObjective f) {
    final boolean[] solution;
    final Random rand;
    final int n;
    int currentUnsatisfied, bestUnsatisfied, indexA, indexB, nextRestart, withoutImprovementOrEqual;

    rand = new Random();
    n = f.getNumberOfVariables();

    solution = new boolean[n];
    nextRestart = 0;
    while (!(f.shouldTerminate())) {
      nextRestart++; // increase restart counter
      withoutImprovementOrEqual = 0; // reset no-improvement counter

      // create the first, random solution
      for (indexA = 0; indexA < n; indexA++) {
        solution[indexA] = rand.nextBoolean();
      }
      bestUnsatisfied = f.evaluate(solution);// and evaluate it

      inner: while (!(f.shouldTerminate())) {
        indexA = rand.nextInt(n); // choose a bit to flip
        indexB = rand.nextInt(n); // choose another bit to flip
        solution[indexA] ^= true; // flip the bit
        if (indexA != indexB) {
          solution[indexB] ^= true; // flip the other bit
        }
        currentUnsatisfied = f.evaluate(solution); // check solution
        if (currentUnsatisfied > bestUnsatisfied) { // if it is worse...
          solution[indexA] ^= true; // ...flip bit again = undo
          if (indexA != indexB) {
            solution[indexB] ^= true; // ...flip other bit again = undo
          }
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

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "2FlipHCrs"; //$NON-NLS-1$
  }

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   */
  public static final void main(final String[] args) {
    new TwoFlipHillClimberWithRestarts().run();
  }
}
