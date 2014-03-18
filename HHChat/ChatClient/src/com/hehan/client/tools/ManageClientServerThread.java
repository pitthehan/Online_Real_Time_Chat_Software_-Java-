/*
 * manage client's connection to server 
 * thread class 
 * 
 */
package com.hehan.client.tools;
import java.util.*;

public class ManageClientServerThread {
	private static HashMap hm = new HashMap<String, ClientServerThread>();
	
	public static void addClientServerThread(String userId, ClientServerThread cst) {
		hm.put(userId, cst);
	}
	
	public static ClientServerThread getClientServerThread(String userId) {
		return (ClientServerThread)hm.get(userId);
	}
}
