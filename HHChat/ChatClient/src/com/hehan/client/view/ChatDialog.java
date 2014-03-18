/**
 * Chat Dialog
 * Since the client need to read, we make it a thread
 * 
 */
package com.hehan.client.view;
import javax.swing.*;

import com.hehan.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.hehan.client.model.*;
import com.hehan.client.tools.ManageClientServerThread;
import com.hehan.client.tools.*;

public class ChatDialog extends JFrame implements ActionListener {
	
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChatDialog chatDialog = new ChatDialog("1");
	}
	
	public ChatDialog(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;
		jta = new JTextArea();
		jtf = new JTextField(18);
		jb = new JButton("Send");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		//this.setIconImage((new ImageIcon("image/photo.gif").getImage()));
		this.setTitle(ownerId +" is chatting with " + friend);
		this.setSize(400, 300);
		this.setVisible(true);
		
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//write a method to show message
	public void showMessage(Message m) {
		String info = m.getSender()+" sends to "+m.getReceiver()+": "+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb) {
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setReceiver(this.friendId);
			m.setMesType(MessageType.message_comm_mes);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			
			//send to server
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientServerThread.getClientServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}

//	public void run() {
//		// TODO Auto-generated method stub
//		while(true) {
//			//read
//			try {
//				//if read nothing, then wait
//				ObjectInputStream ois = new ObjectInputStream(ClientConServer.s.getInputStream());
//				Message m = (Message)ois.readObject();
//				
//				//show message to jta
//				String info = m.getSender()+" sends to "+m.getReceiver()+": "+m.getCon()+"\r\n";
//				this.jta.append(info);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
}
