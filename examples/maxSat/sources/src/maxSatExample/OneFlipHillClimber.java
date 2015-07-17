package maxSatExample;

import java.util.Random;

/**
 * A simple Hill Climbing algorithm which tries to flip a single bit in
 * each iteration and accepts the new solution if it is better.
 */
public final class OneFlipHillClimber extends MaxSatAlgorithm {

  /** create */
  public OneFlipHillClimber() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final void solve(final MaxSatObjective f) {
    final boolean[] solution;
    final Random rand;
    final int n;
    int index;
    int currentUnsatisfied, bestUnsatisfied;

    rand = new Random();
    n = f.getNumberOfVariables();

    // create the first, random solution
    solution = new boolean[n];
    for (index = 0; index < n; index++) {
      solution[index] = rand.nextBoolean();
    }
    bestUnsatisfied = f.evaluate(solution);// and evaluate it

    while (!(f.shouldTerminate())) {
      index = rand.nextInt(n); // choose a bit to flip
      solution[index] ^= true; // flip the bit
      currentUnsatisfied = f.evaluate(solution); // check the new solution
      if (currentUnsatisfied > bestUnsatisfied) { // if it is worse...
        solution[index] ^= true; // ...flip bit again = undo
      } else { // if it is better (or equal)
        bestUnsatisfied = currentUnsatisfied; // keep it and remember
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "1FlipHC"; //$NON-NLS-1$
  }

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   */
  public static final void main(final String[] args) {
    new OneFlipHillClimber().run();
  }
}
