import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo1 {
	public static void main(String[] args) {
		RunnableOne r = new RunnableOne(); //put break point here
		ExecutorService threadPool = Executors.newFixedThreadPool(5); //put break point here
		
		threadPool.execute(r); //pool-1-thread-1
		threadPool.execute(r); //pool-1-thread-2
		threadPool.execute(r); //pool-1-thread-3
		threadPool.execute(r); //pool-1-thread-4
		threadPool.execute(r); //pool-1-thread-5
		
		threadPool.execute(r); //added to queue
		threadPool.execute(r); //added to queue
		threadPool.execute(r); //added to queue

		threadPool.shutdown();
	}
}
