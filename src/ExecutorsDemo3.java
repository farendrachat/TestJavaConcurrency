import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsDemo3 {
	public static void main(String[] args) throws Exception{
		CallableOne c = new CallableOne();
		ExecutorService threadPool = Executors.newCachedThreadPool();

		Future<Integer> future = threadPool.submit(c);
		System.out.println("future done? " + future.isDone()); // may be false
		Integer result = future.get(); // blocks the current thread and waits until the callable task completes before returning the actual result
		System.out.println("future done? " + future.isDone()); // always true
		System.out.print("result: " + result);
	}
}
