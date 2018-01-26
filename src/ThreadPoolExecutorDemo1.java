import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo1 {
	public static void main(String[] args) {
		RunnableOne r = new RunnableOne();
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, queue); //Put break point
		try{
			threadPool.execute(r); //pool-1-thread-1
			threadPool.execute(r); //pool-1-thread-2
		
			threadPool.execute(r); //added to queue
			threadPool.execute(r); //added to queue
			threadPool.execute(r); //added to queue
		
			threadPool.execute(r); //pool-1-thread-3
			threadPool.execute(r); //pool-1-thread-4
		
			threadPool.execute(r); //throws RejectedExecutionHandler  i.e., task is rejected
			threadPool.execute(r); //throws RejectedExecutionHandler  i.e., task is rejected
			threadPool.execute(r); //throws RejectedExecutionHandler  i.e., task is rejected
		}finally{
			threadPool.shutdown();
		}
	}
}
