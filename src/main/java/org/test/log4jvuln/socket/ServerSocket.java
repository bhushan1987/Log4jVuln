package org.test.log4jvuln.socket;

import java.io.*;
import java.net.*;
public class ServerSocket {
    public static void main(String[] args){
        java.net.ServerSocket serverSocket = null;
        try{
            serverSocket = new java.net.ServerSocket(339);
            Socket s=serverSocket.accept();//establishes connection
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  str= dis.readUTF();
            System.out.println("message= "+str);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                try{
                    serverSocket.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}