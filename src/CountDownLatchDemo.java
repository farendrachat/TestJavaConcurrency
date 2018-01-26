import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate How to use CountDownLatch in Java. CountDownLatch
 * is useful if we want to start main processing thread once its dependency is
 * completed as illustrated in this CountDownLatch Example.
 */

public class CountDownLatchDemo {
	/**
	 * Application should not start processing any thread until all services are
	 * up and ready to do their job. The main thread will start with count 3 and
	 * wait until count reaches zero. Each thread once up and read will do a
	 * count down. This will ensure that main thread is not started processing
	 * until all services are up. Count is 3 since we have 3 threads means
	 * services.
	 */
	public static void main(String args[]) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		final CountDownLatch latch = new CountDownLatch(3);
		threadPool.execute(new Service("CacheService", latch));
		threadPool.execute(new Service("AlertService", latch));
		threadPool.execute(new Service("ValidationService", latch));

		try {
			latch.await(); // main thread is disabled until other threads finish their job
			System.out.println("All services are up, Application is starting now");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}finally{
			threadPool.shutdown();
		}
	}
}

/**
 * Service class which will be executed by Thread using CountDownLatch
 * synchronizer.
 */
class Service implements Runnable {
	private final String name;
	private final CountDownLatch latch;

	public Service(String name, CountDownLatch latch) {
		this.name = name;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(name + " is Up");
		latch.countDown(); // reduce count of CountDownLatch by 1
	}
}
