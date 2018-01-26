import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate how to use CyclicBarrier in Java. CyclicBarrier
 * is a new Concurrency Utility added in Java 5 Concurrent package.
 */
public class CyclicBarrierExample {
	// Runnable task for each thread
	private static class Task implements Runnable { //static inner class
		private CyclicBarrier barrier;
		public Task(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
				barrier.await();
				System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
			} catch (InterruptedException ex) {
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(Level.SEVERE, null, ex);
			} catch (BrokenBarrierException ex) {
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void main(String args[]) {
        //creating CyclicBarrier with 3 tasks i.e. 3 Threads needs to call await()
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable(){
            @Override
            public void run(){
                //This task will be executed once all thread reaches barrier
                System.out.println("All parties are arrived at barrier");
            }
        });
		
        //starting each of thread
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(new Task(cb));
        threadPool.execute(new Task(cb));
        threadPool.execute(new Task(cb));
        
        cb.reset();
        
        threadPool.execute(new Task(cb));
        threadPool.execute(new Task(cb));
        threadPool.execute(new Task(cb));
        
        threadPool.shutdown();
    }
}
