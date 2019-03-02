package test;

import java.util.HashMap;
import java.util.Map;

public class Testtry {
	public static void main(String[] args) {
	//	String out = Testtry.testfunc();
	//	System.out.println(out);
		Map<String, String> map = new HashMap<String, String>();
		map.put("abc", "abc");
		map.put("adc", "abc");
		
		for(Map.Entry<String,String> ent :map.entrySet()) {
			System.out.println(ent.getKey()+"---"+ent.getValue());
		}
		
		for(String str:map.keySet()) {
			System.out.println(str+"---"+map.get(str));
		}
		
	}
	
	
	public static String testfunc() {
		try {
			System.out.println();
			//return "insideprogram";
			throw new Exception();
		}catch(Exception e) {
			System.out.println("inside exception");
			e.printStackTrace();
		}finally {
			System.out.println("finally");
			
		}
		return "Trst";
	}
}
