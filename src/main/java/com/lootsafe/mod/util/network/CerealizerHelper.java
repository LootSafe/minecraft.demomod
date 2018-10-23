package com.lootsafe.mod.util.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import io.netty.buffer.ByteBuf;

public class CerealizerHelper {
	
	/* MessageObj */
	
	public ByteBuf cerealizeNetworkItemObj(NetworkMessageObj networkItemObj, ByteBuf buf) throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte[] bytes = null;
		
		try
		{
			out = new ObjectOutputStream(bos);
			out.flush();
			out.writeObject(networkItemObj);
			bytes = bos.toByteArray();	  
		} 
		finally 
		{
			try {
				bos.close();
			} catch (IOException ex) { ex.printStackTrace(); }
		}
		
		buf.writeBytes(bytes);
		
		return buf;
	}
	
	public NetworkMessageObj decerealizeNetworkItemObj(ByteBuf buf) throws IOException, ClassNotFoundException
	{		
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		
		try {			
			in = new ObjectInputStream(bis);
			Object o = in.readObject();		  
			return (NetworkMessageObj) o;
		} 
		finally 
		{
			try {				
			    if (in != null) {
			    	in.close();
			    }		    
			} catch (IOException ex) { ex.printStackTrace(); }		
		}
	}
	
	/* ResponseObj */
	
	public ByteBuf cerealizeNetworkResponseObj(NetworkResponseObj networkResponseObj, ByteBuf buf) throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte[] bytes = null;
		
		try
		{
			out = new ObjectOutputStream(bos);
			out.flush();
			out.writeObject(networkResponseObj);
			bytes = bos.toByteArray();	  
		} 
		finally 
		{
			try {
				bos.close();
			} catch (IOException ex) { ex.printStackTrace(); }
		}
		
		buf.writeBytes(bytes);
		
		return buf;
	}
	
	public NetworkResponseObj decerealizeNetworkResponseObj(ByteBuf buf) throws IOException, ClassNotFoundException
	{		
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		
		try {			
			in = new ObjectInputStream(bis);
			Object o = in.readObject();		  
			return (NetworkResponseObj) o;
		} 
		finally 
		{
			try {				
			    if (in != null) {
			    	in.close();
			    }		    
			} catch (IOException ex) { ex.printStackTrace(); }		
		}
	}

}
