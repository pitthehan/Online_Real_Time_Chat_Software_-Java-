/**
 * Friend List (include stranger and blacklist)
 * 
 */

package com.hehan.client.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.hehan.client.tools.*;
import com.hehan.common.Message;
public class ChatFriendList extends JFrame implements ActionListener, MouseListener {
	//The first card
	JPanel jpf1, jpf2, jpf3;
	JButton jpf_jb1, jpf_jb2, jpf_jb3;
	JScrollPane jsp1;
	
	//The second card(strangers)
	JPanel jps1, jps2, jps3;
	JButton jps_jb1, jps_jb2, jps_jb3;
	JScrollPane jsp2;
	String owner;
	//set JFrame as CardLayout
	CardLayout cl;
	JLabel []jbls;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChatFriendList chatFriendList = new ChatFriendList();
	}
	//update friend list
	public void updateFriend(Message m) {
		String onLineFriend[] = m.getCon().split(" ");
		for(int i=0; i<onLineFriend.length;i++) {
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);;
		}
		
	}
	
	public ChatFriendList(String ownerId) {
		
		this.owner = ownerId; 
		jpf_jb1 = new JButton("My Friends");
		jpf_jb2 = new JButton("Strangers");
		jpf_jb2.addActionListener(this);
		jpf_jb3 = new JButton("Blacklist");
		
		//the first card(show friend list)
		jpf1 = new JPanel(new BorderLayout());
		jpf2 = new JPanel(new GridLayout(50,1,4,4));
		//init 50 friends
		jbls = new JLabel[50];
		for(int i=0; i<jbls.length; i++) {
			jbls[i] = new JLabel(i+1+"", new ImageIcon("image/photo.gif"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId)) {
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jpf2.add(jbls[i]);
		}
		
		jpf3 = new JPanel(new GridLayout(2,1));
		jpf3.add(jpf_jb2);
		jpf3.add(jpf_jb3);
		
		jsp1 = new JScrollPane(jpf2);
		
		jpf1.add(jpf_jb1,"North");
		jpf1.add(jsp1,"Center");
		jpf1.add(jpf3,"South");
		
		//the second card (strangers)
		jps_jb1 = new JButton("My Friends");
		jps_jb1.addActionListener(this);
		jps_jb2 = new JButton("Strangers");
		jps_jb3 = new JButton("Blacklist");
		
		//the first card(show friend list)
		jps1 = new JPanel(new BorderLayout());
		
		jps2 = new JPanel(new GridLayout(20,1,4,4));
		//init 20 strangers
		JLabel []jbls2 = new JLabel[20];
		for(int i=0; i<jbls2.length; i++) {
			jbls2[i] = new JLabel(i+1+"", new ImageIcon("image/photo.gif"),JLabel.LEFT);
			jps2.add(jbls2[i]);
		}
		
		jps3 = new JPanel(new GridLayout(2,1));
		jps3.add(jps_jb1);
		jps3.add(jps_jb2);
		
		jsp2 = new JScrollPane(jps2);
		
		jps1.add(jps3,"North");
		jps1.add(jsp2,"Center");
		jps1.add(jps_jb3,"South");
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jpf1,"1");//the first card
		this.add(jps1,"2");//the second card
		//show owner id
		this.setTitle(ownerId);
		this.setSize(200, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//click stranger button, show the second card
		if(e.getSource() == jpf_jb2) {
			cl.show(this.getContentPane(), "2");///!!!
		} else if(e.getSource() == jps_jb1) {
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//response to double-click event, then get id
		if(e.getClickCount()==2) {
			//get id
			String friendNo = ((JLabel)e.getSource()).getText();
			//System.out.println(friendNo);
			ChatDialog cdl = new ChatDialog(this.owner, friendNo);
			//add chatdialog to managechatdialog
			ManageChatDialog.addChatDialog(this.owner+" "+friendNo, cdl);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
}
