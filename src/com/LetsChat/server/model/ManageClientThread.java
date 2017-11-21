/**
 * Fucntion:
 * 1, add the <uId,scct> to the HaspMap
 * 2, get the scct by getClientThread(uId) method
 * 3, check whether the client is online/offline:
 */
package com.LetsChat.server.model;

import java.util.HashMap;

public class ManageClientThread {

	public static HashMap hm = new HashMap<String, SerConClientThread>();
	
	public static void addClientThread(String uId, SerConClientThread scct){
		//add to the hashMap, with uId as key, and scct as Value.
		hm.put(uId, scct); 
	}
	public static SerConClientThread getClientThread(String uId){
		return (SerConClientThread) hm.get(uId);
	}
}
