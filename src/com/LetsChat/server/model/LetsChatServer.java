/**
 * The server ss Listening and waiting for client
 * 1, when get the Login info, verify, send a message m, open a scct and add to the HashMap.
 */

package com.LetsChat.server.model;
import java.net.*;
import java.io.*;
import java.util.*;

import com.LetsChat.common.Message;
import com.LetsChat.common.User;

public class LetsChatServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public LetsChatServer(){
		ServerSocket ss = null;
		try {
			//listen at port 9999
			System.out.println("I'm server, waiting at 9999...");
			ss = new ServerSocket(9999);
			//Keep listening
			while(true){
				//waiting for the connection
				Socket s = ss.accept();
				//Use the String from a bufferedReader.readLine() will get trouble.
				//here using a object stream: User (client send User first time)
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u= new User();
				try {
					u = (User) ois.readObject();
					System.out.println("Server recieved the client id: "+u.getUserId()+" ,pwd: "+u.getPwd());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				if(u.getPwd().equals("12345")){	
					//"1" means the pwd is correct
					m.setMsgType("1");
					//return a Login-Success info to the client
					oos.writeObject(m);
					//open a separate thread for the client's connection
					SerConClientThread scct = new SerConClientThread(s);
					//add the scct to the HashMap
					ManageClientThread.addClientThread(u.getUserId(), scct);
					scct.start(); //call the run() method of this thread scct.
 
					//inform other online friends that Im just online
					scct.notifyOthers(u.getUserId());
					
				}else{
					//"2" means the pwd is incorrect
					m.setMsgType("2");
					oos.writeObject(m);
					//then close the connection
					s.close();
				}
				//System.out.println(m.getMsgType());

			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
