/**
 * A Thread that server uses to manage the connection with a client.
 */
package com.LetsChat.server.model;

import java.net.*;
import java.io.*;
import com.LetsChat.common.*;

public class SerConClientThread extends Thread{
	Socket s;
	public SerConClientThread(Socket s){
		//give the socket to the thread
		this.s = s;

	}
	
	public void run(){
		while(true){
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				//server get the msg from the client
				System.out.println(m.getSender()+" send a msg to "+ m.getReceiver()+" : "+m.getContent());
			
				/*
				 * Transmit the msg to another client
				 */
				//get the thread of the "another client" from the HashMap
				SerConClientThread sc = ManageClientThread.getClientThread(m.getReceiver());
				ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
		}
	}
}







