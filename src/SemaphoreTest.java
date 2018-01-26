import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * A counting semaphore with one permit is known as binary semaphore because it
 * has only two states such as permit available or permit unavailable. Binary
 * semaphore can be used to implement mutual exclusion or critical section where
 * only one thread is allowed to execute. Thread will wait on acquire() until
 * Thread inside critical section release permit by calling release() on
 * semaphore.
 */
public class SemaphoreTest {
	Semaphore binary = new Semaphore(2); //one permit here

	public static void main(String args[]) {
		final SemaphoreTest test = new SemaphoreTest();
		ExecutorService threadPool = Executors.newFixedThreadPool(1);

		threadPool.execute(()-> test.mutualExclusion());
		threadPool.execute(()-> test.mutualExclusion());
		
		threadPool.shutdown();
	}

	private void mutualExclusion() {
		try {
			binary.acquire();
			// mutual exclusive region. Restricting the threads to acquire the semaphore before entering the critical region.
			System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");
		//	Thread.sleep(1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			binary.release();
			System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
		}
	}
}
