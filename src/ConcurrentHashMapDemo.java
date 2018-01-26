import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//use jdk1.7
public class ConcurrentHashMapDemo {
	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<String, String>(); //put break point here
		
		map.put("India", "Delhi");
		map.put("Japan", "Tokyo");
		map.put("France", "Paris");
		map.put("Russia", "Moscow");
		map.put("Pakistan","Islamabad");
		map.put("Srilanka","Colombo");
		
		Iterator<String> ittr = map.keySet().iterator(); //put break point here
		while (ittr.hasNext()) {
			String countryObj = ittr.next();
			map.put("abc", "xyz"); //Ok.
			String capital = map.get(countryObj);
			System.out.println(countryObj + "----" + capital);
		}
	}
}
