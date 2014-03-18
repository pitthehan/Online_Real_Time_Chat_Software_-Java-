/*
 * thread to connect server and client
 */
package com.hehan.client.tools;
import java.io.*;
import java.net.*;

import com.hehan.client.view.ChatDialog;
import com.hehan.client.view.ChatFriendList;
import com.hehan.common.*;
import com.hehan.client.view.*;

public class ClientServerThread extends Thread{
	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientServerThread(Socket s) {
		this.s = s;
	}
	
	public void run() {
		while(true) {
			//keep reading messages from server
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				System.out.println("Server sends" + m.getSender() + m.getReceiver() +m.getCon());
				
				if(m.getMesType().equals(MessageType.message_comm_mes)) {
					//show the message to destination interface
					ChatDialog chatDialog = ManageChatDialog.getChatDialog(m.getReceiver()+" "+m.getSender());
					chatDialog.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					String con = m.getCon();
					String friends[] = con.split(" ");
					String receiver = m.getReceiver();
					//change corresponding friend list
					ChatFriendList chatFriendList = ManageChatFriendList.getChatFriendList(receiver);
					
					//update online friendlist
					if(chatFriendList!=null) {
						chatFriendList.updateFriend(m);
					}
					
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
