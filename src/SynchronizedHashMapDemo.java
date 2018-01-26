import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SynchronizedHashMapDemo {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(); //but break point here
		Collections.synchronizedMap(map); //becomes synchronized hashmap
		
		
		map.put("India", "Delhi");
		map.put("Japan", "Tokyo");
		map.put("France", "Paris");
		map.put("Russia", "Moscow");
		map.put("Pakistan","Islamabad");
		map.put("Srilanka","Colombo");
		
		Iterator<String> ittr = map.keySet().iterator(); 
		while (ittr.hasNext()) {
			String countryObj = ittr.next();
			map.put("abc", "xyz"); //throws ConcurrentModificationException
			String capital = map.get(countryObj);
			System.out.println(countryObj + "----" + capital);
		}
	}
}

