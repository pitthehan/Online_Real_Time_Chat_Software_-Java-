/**
 * Thread to connect server and a certain client 
 * 
 */
package com.hehan.server.model;
import java.net.*;
import java.io.*;

import com.hehan.common.*;

import java.util.*;

public class ServerClientThread extends Thread {
	Socket s;
	
	public ServerClientThread(Socket s) {
		//give the connect to s
		this.s = s;
	}
	
	//let this thread notifies other online users
	public void notifyOther(String iam) {
		//get all the online threads
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext()) {
			
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos =new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setReceiver(onLineUserId);
				oos.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		while(true) {
			//this thread can receive client's message
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				//System.out.println(m.getSender()+m.getReceiver()+m.getCon());
				//According to message type from client
				if(m.getMesType().equals(MessageType.message_comm_mes)) {
				
					//forward sender's message to receiver
					//get receiver's thread
					ServerClientThread sct = ManageClientThread.getClientThread(m.getReceiver());
					ObjectOutputStream oos = new ObjectOutputStream(sct.s.getOutputStream());
					oos.writeObject(m);
				} else if(m.getMesType().equals(MessageType.messge_get_onLineFriend)) {
					//the server return to this client
					String res = ManageClientThread.getAllonlineUserid();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setReceiver(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
					
				}
			} catch(Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
