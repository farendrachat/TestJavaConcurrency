import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
 }

public class ThreadPoolExecutorDemo2 {
	public static void main(String[] args) {
		RunnableOne r = new RunnableOne();
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);
		RejectedExecutionHandlerImpl rejectHandler = new RejectedExecutionHandlerImpl();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, queue, rejectHandler); //Put break point
		try{
			threadPool.execute(r); //pool-1-thread-1
			threadPool.execute(r); //pool-1-thread-2
		
			threadPool.execute(r); //added to queue
			threadPool.execute(r); //added to queue
			threadPool.execute(r); //added to queue
		
			threadPool.execute(r); //pool-1-thread-3
			threadPool.execute(r); //pool-1-thread-4
		
			threadPool.execute(r); //task is rejected but handled
			threadPool.execute(r); //task is rejected but handled
			threadPool.execute(r); //task is rejected but handled
		}finally{
			threadPool.shutdown();
		}
	}
}
