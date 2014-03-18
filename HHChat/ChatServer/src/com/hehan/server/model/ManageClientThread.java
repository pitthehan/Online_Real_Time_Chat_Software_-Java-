package com.hehan.server.model;
import java.util.*;

public class ManageClientThread {
 
	//only one hashmap, so use static. 
	//if not static, we may have more that one hashmap, which is not good
	public static HashMap hm = new HashMap<String, ServerClientThread >();
	//add client thread to hm
	public static void addClientThread(String uid, ServerClientThread ct) {
		hm.put(uid, ct);
	}
 
	public static ServerClientThread getClientThread(String uid) {
		return (ServerClientThread)hm.get(uid);
	}
 
	//return current online users
	public static String getAllonlineUserid() {
		//use iterator
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()) {
			res += it.next().toString()+" ";
		}
		return res;
	}
}

