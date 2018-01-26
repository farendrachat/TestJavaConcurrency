import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockSolution {
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String args[]) {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		threadPool.execute(new ThreadDemo1());
		threadPool.execute(new ThreadDemo2());
		threadPool.shutdown(); //put debug here
	}

	private static class ThreadDemo1 implements Runnable {
		public void run() {
			synchronized (lock2) {
				System.out.println("Thread 1: Holding lock 1...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.println("Thread 1: Waiting for lock 2...");
				synchronized (lock1) {
					System.out.println("Thread 1: Holding lock 1 & 2...");
				}
			}
		}
	}

	private static class ThreadDemo2 implements Runnable {
		public void run() {
			synchronized (lock2) {
				System.out.println("Thread 2: Holding lock 2...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.println("Thread 2: Waiting for lock 1...");
				synchronized (lock1) {
					System.out.println("Thread 2: Holding lock 1 & 2...");
				}
			}
		}
	}
}
