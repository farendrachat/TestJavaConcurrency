public class MultihreadDemo {
	public static void main(String[] args) {
		RunnableOne r = new RunnableOne(); //put break point here
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);

		t1.start();
		t2.start();
	}
}
