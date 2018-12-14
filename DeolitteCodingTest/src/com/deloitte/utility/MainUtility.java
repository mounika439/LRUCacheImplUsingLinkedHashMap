package com.deloitte.utility;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.deloitte.cache.LRUCache;

public class MainUtility {
	
	
	public static void main(String[] args) {
		LRUCache<String, String> cache = LRUCache.newInstance(3);
		
		System.out.println("result = "+ encryptNameHelper("Sridhar",cache)+ "\n");
		
		System.out.println("result = "+ encryptNameHelper("Sridhar",cache)+ "\n");
				
		System.out.println("result = "+ encryptNameHelper("Amin",cache)+ "\n");
				
		System.out.println("result = "+ decryptNameHelper("inAm", cache)+ "\n");
		
		System.out.println("result = "+ decryptNameHelper("ilAn",cache)+ "\n");
		
		System.out.println("result = "+ decryptNameHelper("ilAn",cache)+ "\n");
	}
	
	public static String encryptNameHelper(String name,LRUCache<String, String> cache){
		System.out.println("encrypting "+name);
		if(cache.containsKey(name)){
			System.out.println(name+" found in the cache");
			return cache.get(name);
		}else{
			String decryptName = encryptName(name);
			System.out.println("Adding to the cache ");
			cache.put(name, decryptName);
			return decryptName;
		}
	}
	
	public static String decryptNameHelper(String dName,LRUCache<String, String> cache){
		System.out.println("decrypting "+dName);
		
		if(cache.containsValue(dName)){
			System.out.println(dName +" found in cache");
			return getKeyFromValue(cache,dName);
			
		}else{
		
			String source = decryptName(dName);
			System.out.println("Adding to the cache");
			cache.put(source, dName);
			return source;
		}
	}
	
	
	public static String getKeyFromValue(LinkedHashMap<String, String> map,String val){
		try{
			Iterator<Map.Entry<String,String>> it;
			Entry<String,String> entry;
		
			it = map.entrySet().iterator();
		
			while(it.hasNext()){
				entry = it.next();
				if(entry.getValue().equals(val)){
					return entry.getKey();
				}
			}
		}	
		catch(ConcurrentModificationException ce){
			System.out.println(ce.getMessage());
		}
		return null;
	}
	
	
	public static String encryptName(String sourceName){
		
		StringBuilder resStr = new StringBuilder();
		
		try{
			int len = sourceName.length();
			Character ch = '#';
		
			if(len%2 != 0){
				sourceName = sourceName +ch;
			}
		
			int mid = sourceName.length()/2;
			resStr.append(sourceName.substring(mid));
			resStr.append(sourceName.substring(0,mid));
		
			//sourceName = sourceName.replace("#", "");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return resStr.toString();
		
	}
	
	public static String decryptName(String decryptName){
		StringBuilder resStr = new StringBuilder();
		try{
			String secondhalf = decryptName.substring(decryptName.length()/2);
			String firsthalf = decryptName.substring(0, decryptName.length()/2);
			firsthalf = firsthalf.replace("#","");
			resStr.append(secondhalf);
			resStr.append(firsthalf);
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resStr.toString();
	}
}