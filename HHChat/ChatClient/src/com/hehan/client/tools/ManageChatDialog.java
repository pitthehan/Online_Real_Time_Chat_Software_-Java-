/*
 * control chat dialog
 */
package com.hehan.client.tools;
import java.util.*;
import com.hehan.client.view.*;
public class ManageChatDialog {
	private static HashMap hm = new HashMap<String, ChatDialog>();
	
	//add
	public static void addChatDialog(String loginIdandFriendId, ChatDialog chatDialog) {
		hm.put(loginIdandFriendId, chatDialog);
		
	}
	//get
	public static ChatDialog getChatDialog(String loginIdandFriendId) {
		return (ChatDialog)hm.get(loginIdandFriendId);
	}
}
