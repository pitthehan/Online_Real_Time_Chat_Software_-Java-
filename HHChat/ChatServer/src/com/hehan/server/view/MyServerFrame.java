/**
 * server control interface
 * start server
 * close server
 * manage and monitor user
 * 
 */
package com.hehan.server.view;

import javax.swing.*;

import com.hehan.server.model.MyServer;
import com.hehan.server.model.*;
import java.awt.*;
import java.awt.event.*;

public class MyServerFrame extends JFrame implements ActionListener{
	
	JPanel jp1;
	JButton jb1, jb2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf = new MyServerFrame();
	}
	
	public MyServerFrame() {
		jp1 = new JPanel();
		jb1 = new JButton("Start Server");
		jb1.addActionListener(this);
		jb2 = new JButton("Close Server");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1) {
			new MyServer();
		}
	}
}
