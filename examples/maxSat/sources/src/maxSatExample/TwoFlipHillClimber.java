package maxSatExample;

import java.util.Random;

/**
 * A Hill Climbing algorithm which tries to flip one or two bits in each
 * iteration and accepts the new solution if it is better.
 */
public final class TwoFlipHillClimber extends MaxSatAlgorithm {

  /** create */
  public TwoFlipHillClimber() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final void solve(final MaxSatObjective f) {
    final boolean[] solution;
    final Random rand;
    final int n;
    int currentUnsatisfied, bestUnsatisfied, indexA, indexB;

    rand = new Random();
    n = f.getNumberOfVariables();

    // create the first, random solution
    solution = new boolean[n];
    for (indexA = 0; indexA < n; indexA++) {
      solution[indexA] = rand.nextBoolean();
    }
    bestUnsatisfied = f.evaluate(solution);// and evaluate it

    while (!(f.shouldTerminate())) {
      indexA = rand.nextInt(n); // choose a bit to flip
      indexB = rand.nextInt(n); // choose another bit to flip
      solution[indexA] ^= true; // flip the bit
      if (indexA != indexB) {
        solution[indexB] ^= true; // flip the other bit
      }
      currentUnsatisfied = f.evaluate(solution); // check solution
      if (currentUnsatisfied > bestUnsatisfied) { // compare
        solution[indexA] ^= true; // ...flip bit again = undo
        if (indexA != indexB) {
          solution[indexB] ^= true; // ...flip other bit again = undo
        }
      } else { // otherwise, remember objective value
        bestUnsatisfied = currentUnsatisfied;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "2FlipHC"; //$NON-NLS-1$
  }

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   */
  public static final void main(final String[] args) {
    new TwoFlipHillClimber().run();
  }
}
