import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate How to use CountDownbarrier in Java. CountDownbarrier
 * is useful if we want to start main processing thread once its dependency is
 * completed as illustrated in this CountDownbarrier Example.
 */

public class CyclicBarrierDemo {
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
		final CyclicBarrier barrier = new CyclicBarrier(3);
		threadPool.execute(new Service1("CacheService", barrier));
		threadPool.execute(new Service1("AlertService", barrier));
		threadPool.execute(new Service1("ValidationService", barrier));

		try {
			try {
				barrier.await();
				barrier.reset();
				threadPool.execute(new Service1("CacheService2", barrier));
				threadPool.execute(new Service1("AlertService2", barrier));
				threadPool.execute(new Service1("ValidationService2", barrier));
				barrier.await();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // main thread is disabled until other threads finish their job
			System.out.println("All services are up, Application is starting now");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}finally{
			threadPool.shutdown();
		}
	}
}

/**
 * Service class which will be executed by Thread using CountDownbarrier
 * synchronizer.
 */
class Service1 implements Runnable {
	private final String name;
	private final CyclicBarrier barrier;

	public Service1(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(name + " is Up");
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // reduce count of CountDownbarrier by 1
		System.out.println("await "+name+" finished");
	}
}
