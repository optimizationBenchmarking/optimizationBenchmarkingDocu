package maxSatExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** Execute all experiments one by one. */
public final class RunAll {

  /**
   * The main method: apply the algorithm to all instances
   *
   * @param args
   *          ignored
   * @throws Throwable
   *           if something goes wrong
   */
  public static final void main(final String[] args) throws Throwable {
    final ExecutorService service;
    int processors;

    processors = Runtime.getRuntime().availableProcessors();
    processors = Math.max(1,//
        Math.max((processors >> 1), (processors - 1)));
    MaxSatAlgorithm._print("Using " + processors + //$NON-NLS-1$
        " processors to run experiments."); //$NON-NLS-1$
    service = Executors.newFixedThreadPool(processors);

    Thread.sleep(12000);

    service.submit(new OneFlipHillClimber());
    service.submit(new OneFlipHillClimberWithRestarts());
    service.submit(new TwoFlipHillClimberWithRestarts());
    service.submit(new TwoFlipHillClimber());
    service.submit(new TwoFlipHillClimberWithRestarts());
    service.submit(new MFlipHillClimber());
    service.submit(new MFlipHillClimberWithRestarts());

    service.shutdown();
    service.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    MaxSatAlgorithm._print("Finished running all experiments."); //$NON-NLS-1$
  }
}
