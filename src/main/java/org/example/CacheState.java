package org.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CacheState {
	
	private static Map<String, String> map = new LinkedHashMap<String, String>();
	private static CacheState instance = null;

	protected CacheState(Properties props) {
		if (props != null){  
			for (final String name: props.stringPropertyNames())
				map.put(name, props.getProperty(name));
		}
	}
	
	public static CacheState getInstance(Properties props) {
		
		if (instance == null) instance = new CacheState(props);
		
		return instance;
	}
	
	public static void setCache(String key, String val) {
		if (map != null)
		map.put(key, val);
	}
	
	public static boolean cacheFound(String key) {
		if(map == null) return false;
		return map.containsKey(key);
	}
	
	public static String getCache(String key) {
		if (map == null) return null;
		return map.get(key);
	}
	
	public static Properties getProperties() {
	   Properties p = new Properties();
	   Set<Map.Entry<String,String>> set = map.entrySet();
	   for (Map.Entry<String,String> entry : set) {
	     p.put(entry.getKey(), entry.getValue());
	   }
	   return p;
	}

}
