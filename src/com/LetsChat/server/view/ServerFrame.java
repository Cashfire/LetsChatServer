/**
 * The control window of server:
 * Function: start, close, or force offline server
 */
package com.LetsChat.server.view;

import javax.swing.JFrame;

import com.LetsChat.server.model.LetsChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerFrame extends JFrame implements ActionListener{
	JPanel jp;
	JButton jb1, jb2;
	
	
	public static void main(String[] args) {
		
		ServerFrame sf = new ServerFrame();
	}
	
	public ServerFrame(){
		jp = new JPanel();
		jb1 = new JButton("Start");
		jb1.addActionListener(this);
		jb2 = new JButton("Close");
		jb2.addActionListener(this);
		jp.add(jb1);
		jp.add(jb2);
		
		this.add(jp);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource()==jb1){
			new LetsChatServer();
		}
		if(evt.getSource()==jb2){
			//close
		}
	}

}
