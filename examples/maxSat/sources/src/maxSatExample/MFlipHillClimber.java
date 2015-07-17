package maxSatExample;

import java.util.Random;

/**
 * An {@code m}-flip Hill Climbing algorithm: In each iteration, group of
 * <code>1&le;m&le;n</code> bits are chosen for being flipped. If the
 * resulting solution is better than the old one, it is kept. Otherwise,
 * the bits are flipped again to revert the change. After a given bit has
 * been chosen for flipping, this bit will not be chosen again until every
 * other bit has been chosen. This eases implementation. The number
 * {@code m} is chosen randomly according to a pseudo-geometric
 * distribution (it is not really geometric, due to the limited range).
 */
public final class MFlipHillClimber extends MaxSatAlgorithm {

  /** create */
  public MFlipHillClimber() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final void solve(final MaxSatObjective f) {
    final boolean[] solution;
    final int[] permutation;
    final Random rand;
    final int n;
    int currentUnsatisfied, bestUnsatisfied, index, permIndex, flipCount;

    rand = new Random();
    n = f.getNumberOfVariables();

    // create a permutation of the numbers from 0..1
    permutation = new int[n];
    for (index = 0; index < n; index++) {
      permutation[index] = index;
    }
    MFlipHillClimber.__shuffle(permutation, rand); // shuffle it
    permIndex = 0; // the index of the next index

    // create the first, random solution
    solution = new boolean[n];
    for (index = 0; index < n; index++) {
      solution[index] = rand.nextBoolean();
    }
    bestUnsatisfied = f.evaluate(solution); // and evaluate it

    while (!(f.shouldTerminate())) {// while we can continue

      // choose the number of bits to flip
      flipCount = MFlipHillClimber.__geometric(
          (permutation.length - permIndex), rand);
      for (index = 0; index < flipCount; index++) { // flip them
        solution[permutation[permIndex + index]] ^= true;
      }

      currentUnsatisfied = f.evaluate(solution); // evaluate new solution
      if (currentUnsatisfied > bestUnsatisfied) { // if worse than before
        for (index = 0; index < flipCount; index++) {
          solution[permutation[permIndex + index]] ^= true; // undo
        }
      } else { // otherwise, remember objective value
        bestUnsatisfied = currentUnsatisfied;
      }

      permIndex += flipCount;// increase index in permutation
      if (permIndex >= permutation.length) {// after all indexes are used
        // shuffle indexes randomly
        MFlipHillClimber.__shuffle(permutation, rand);
        permIndex = 0;
      }
    }
  }

  /**
   * Shuffle an array of integers by using the Knuth aka. Fisher-Yates
   * shuffle.
   *
   * @param data
   *          the integer array to shuffle
   * @param rand
   *          the random number generator
   */
  private static final void __shuffle(final int[] data, final Random rand) {
    final int end;
    int i, j, temp;

    end = (data.length - 2);
    for (i = 0; i <= end; i++) {
      j = (i + rand.nextInt(data.length - i));
      temp = data[i];
      data[i] = data[j];
      data[j] = temp;
    }
  }

  /**
   * This method returns a pseudo-geometrically distributed random number
   * with {@code p=0.5}. The value of this number will be in {@code 1..max}
   * . It is assumed that {@code max} is relatively small.
   *
   * @param max
   *          the maximum allowed result (the minimum is {@code 1})
   * @param rand
   *          the random number generator
   * @return the random number
   */
  private static final int __geometric(final int max, final Random rand) {
    int count;

    count = 1;
    while ((count < max) && rand.nextBoolean()) {
      count++;
    }
    return count;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "mFlipHC"; //$NON-NLS-1$
  }

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   */
  public static final void main(final String[] args) {
    new MFlipHillClimber().run();
  }
}
