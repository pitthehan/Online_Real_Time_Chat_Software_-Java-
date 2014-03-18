/**
 * Client, connect to server
 * 
 */
package com.hehan.client.model;

import java.util.*;
import java.net.*;
import java.io.*;
import com.hehan.common.*;
import com.hehan.client.tools.*;

public class ClientConServer {

	public Socket s;
	
	//send the first request
	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("127.0.0.1",6666);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Message ms = (Message)ois.readObject();
			//check user
			if(ms.getMesType().equals("1")) {
				//create this user's connection to server (thread)
				ClientServerThread cst = new ClientServerThread(s);
				//start this thread
				cst.start();
				ManageClientServerThread.addClientServerThread(((User)o).getUserId(), cst);
				b = true;
			} else {
				s.close();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		return b;
	}
	
	public void SendInfoToServer(Object o) {
		try {
			Socket s = new Socket("127.0.0.1",6666);
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
}
