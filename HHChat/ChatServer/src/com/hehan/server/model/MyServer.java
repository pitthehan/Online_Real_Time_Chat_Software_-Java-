/**
 * Server, listen, wait for connection from client
 * 
 */
package com.hehan.server.model;
import java.net.*;
import java.io.*;
import java.util.*;

import com.hehan.common.Message;
import com.hehan.common.User;
import com.hehan.common.*;

public class MyServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MyServer() {
		
		try {
			System.out.println("Server listen 6666");
			ServerSocket ss = new ServerSocket(6666);
			//block, wait for connection
			while(true) {
			Socket s = ss.accept();
			
			//receive message from client
			//BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//String info = br.readLine();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			User u = (User)ois.readObject();
			Message m = new Message();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			if(u.getPasswd().equals("hehan")) {
				//return login 
				m.setMesType("1");
				oos.writeObject(m);
				
				//create a thread to connect this client
				ServerClientThread sct = new ServerClientThread(s);
				//add to hashmap
				ManageClientThread.addClientThread(u.getUserId(), sct);
				//start thread
				sct.start();
				//notify other online users
				sct.notifyOther(u.getUserId());
			} else {
				m.setMesType("2");
				oos.writeObject(m);
				//close connection
				s.close();
			}
			
			
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
