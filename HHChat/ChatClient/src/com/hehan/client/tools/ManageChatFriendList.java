/*
 * Manage friend list, blacklist.. interface class
 * 
 */
package com.hehan.client.tools;
import java.util.*;
import java.io.*;

import com.hehan.client.view.*;

public class ManageChatFriendList {
	private static HashMap hm = new HashMap<String,ChatFriendList>();
	
	public static void addChatFriendList(String userId, ChatFriendList chatFriendList) {
		hm.put(userId, chatFriendList);
	}
	
	public static ChatFriendList getChatFriendList(String userId) {
		return (ChatFriendList)hm.get(userId);
	}
}
