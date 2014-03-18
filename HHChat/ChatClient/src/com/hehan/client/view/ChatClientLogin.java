/**
 * Chat Client Login Interface
 * 
 */
package com.hehan.client.view;

import javax.swing.*;

import com.hehan.client.model.ChatClientUser;
import com.hehan.client.model.ClientConServer;
import com.hehan.common.User;
import com.hehan.client.model.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.hehan.client.tools.*;
import com.hehan.common.*;

public class ChatClientLogin extends JFrame implements ActionListener {

	//North component
	JLabel jbl1;
	
	//Center component
	//3 JPanel tab control
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4; 
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	
	//South component
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;
	
	
	public static void main(String [] args) {
		ChatClientLogin chatClientLogin = new ChatClientLogin();
	}
	
	public ChatClientLogin() {
		//North
		jbl1 = new JLabel("He Han",JLabel.CENTER);
		
		//Center  
		jp2 = new JPanel(new GridLayout(3,3));
		jp2_jbl1 = new JLabel("User ID", JLabel.CENTER);
		jp2_jbl2 = new JLabel("Password", JLabel.CENTER);
		jp2_jbl3 = new JLabel("Forget Password", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("Protect Password", JLabel.CENTER);
		jp2_jb1 = new JButton("Clear");
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("Invisible Login");
		jp2_jcb2 = new JCheckBox("Remember Me");
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		jtp = new JTabbedPane();
		jtp.add("Chat ID",jp2);
		jp3 = new JPanel();
		jtp.add("Mobile ID",jp3);
		jp4 = new JPanel();
		jtp.add("Email",jp4);
		
		//South
		jp1 = new JPanel(); //default flowlayout
		jp1_jb1 = new JButton("Login"); 
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton("Cancel"); 
		jp1_jb3 = new JButton("Guide"); 
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp1_jb1) {
			ChatClientUser chatClientUser = new ChatClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(chatClientUser.checkUser(u)) {
				
				
				try {
					//create friendlist 
					ChatFriendList chatFriendList = new ChatFriendList(u.getUserId());
					ManageChatFriendList.addChatFriendList(u.getUserId(), chatFriendList);
					
					//send request for online friends
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientServerThread.getClientServerThread(u.getUserId()).getS().getOutputStream());
					
					//create Message
					Message m = new Message();
					m.setMesType(MessageType.messge_get_onLineFriend);
					//request this user's friend list
					m.setSender(u.getUserId());
					oos.writeObject(m);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//close login interface
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "UserId or Password Error");
			}
			  
		}
	}
}
