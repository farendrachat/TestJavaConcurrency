import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo2 {
	public static void main(String[] args) {
		RunnableOne r = new RunnableOne(); //put break point here
		ExecutorService threadPool = Executors.newCachedThreadPool(); //put break point here
		
		threadPool.execute(r); //pool-1-thread-1
		threadPool.execute(r); //pool-1-thread-2
		threadPool.execute(r); //pool-1-thread-3
		threadPool.execute(r); //pool-1-thread-4
		threadPool.execute(r); //pool-1-thread-5
		
		threadPool.execute(r); //pool-1-thread-6
		threadPool.execute(r); //pool-1-thread-7
		threadPool.execute(r); //pool-1-thread-8

		//threadPool.shutdown(); //not required
	}
}
