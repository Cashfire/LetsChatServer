/**
 * A Thread that server uses to manage the connection with a client.
 */
package com.LetsChat.server.model;

import java.net.*;
import java.io.*;
import com.LetsChat.common.*;
import java.util.*;

public class SerConClientThread extends Thread{
	private Socket s;
	public SerConClientThread(Socket s){
		//give the socket to the thread
		this.s = s;

	}
	public void notifyOthers(String newUserId){
		//get the hashMap of the online list
		HashMap hm = ManageClientThread.hm;
		Iterator it= hm.keySet().iterator();
		while(it.hasNext()){
			Message m = new Message();
			m.setContent(newUserId);
			m.setMsgType(MessageType.msg_return_onLineFriend);
			
			//get the id of online users
			String onlineUserId = it.next().toString();
			try{
			ObjectOutputStream oos= new ObjectOutputStream(ManageClientThread.getClientThread(onlineUserId).s.getOutputStream());
			m.setReceiver(onlineUserId);
			oos.writeObject(m);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void run(){
		try{
		while(true){
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				//server get the msg from the client
				if(m.getMsgType().equals(MessageType.msg_common)){ //if common message chatting to another client
					/*
					 * Transmit the msg to another client
					 */
					//get the thread of the "another client" from the HashMap
					SerConClientThread scct = ManageClientThread.getClientThread(m.getReceiver());
					ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMsgType().equals(MessageType.msg_get_onLineFriend)){ //if request online friend
					System.out.println(m.getSender()+" request online friendlist ");
					//from server return the online friend list to client
					String res = ManageClientThread.getAllOnlineFriend();
					//server reply the client who request onlineFriend list
					Message reply = new Message();
					reply.setMsgType(MessageType.msg_return_onLineFriend);;
					reply.setContent(res);
					reply.setReceiver(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(reply);
					
				}
					
			}
		}	catch(EOFException e) {
				System.out.println("Client exit");
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
	}
}







