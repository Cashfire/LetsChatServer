/**
 * The server ss Listening and waiting for client
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
		try {
			//listen at port 9999
			System.out.println("I'm server, waiting at 9999...");
			ServerSocket ss = new ServerSocket(9999);
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
			
			Message m = new Message();
			if(u.getPwd().equals("12345")){	
				//"1" means the pwd is correct
				m.setMsgType("1");
			}else{
				//"2" means the pwd is incorrect
				m.setMsgType("2");
			}
			System.out.println(m.getMsgType());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
