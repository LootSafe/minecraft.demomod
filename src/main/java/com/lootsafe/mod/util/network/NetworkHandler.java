package com.lootsafe.mod.util.network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.JsonObject;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;

import io.lootsafe.api.ServiceProvider;
import io.lootsafe.api.Requests.NodeHandler;

public class NetworkHandler {
		
    private NodeHandler nh;
    private ServiceProvider sv;    
    
    public NetworkHandler(){
    	
        sv = new ServiceProvider.ServiceBuilder()
        		.withVersion(Reference.version)
		        .withHost(Reference.hostAddress + Reference.hostPort)
		        .withPrivateKey(Reference.privateKey)
		        .withDebug(Reference.debug)
		        .build();
        
		sv.startService();
		
		if (sv.isWorking()) 
		{
		    nh = sv.getNodeHandler();
		    nh.test();
		} 
		else 
		{
		    throw new RuntimeException("Could not start up the LootSafe Service! Is your config in order?");
		}	    			
    }
    
    public boolean GivePlayerItem(String playerName, String playerAddress, String uniqueItemAddress) 
    {	
        try 
        {            
        	JsonObject response = nh.postSpawnItem(uniqueItemAddress, playerAddress);
        	
        	if (response.getInt("status") == 200) 
        	{
        		Main.proxy.PlayerItemWalletSuccess(playerName, uniqueItemAddress);
        		return true;
        	}
        	else
        	{
        		System.out.println("ERROR CALLING THE API: " + response.getInt("status")); 
        		return false;        		
        	}
        	
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR CALLING THE API, Response wasn't 200"); 
            //e.printStackTrace();
            return false;
        } 
        
    }
    
    public String getUniqueAddress(String itemAddress)
    {
    	return mockUniqueAddress(itemAddress);
    }
    
    public String mockUniqueAddress(String itemAddress)
    {    	
    	MessageDigest md;
        StringBuffer sb = new StringBuffer();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date date = new Date();
    	
    	String itemAddressMD5 = dateFormat.format(date) + " " + itemAddress;
    	    	
    	sb.append("0x");
    	
		try 
		{			
			md = MessageDigest.getInstance("MD5");
	        md.update(itemAddressMD5.getBytes());
	        
	        byte byteData[] = md.digest();
	 
	        for (int i = 0; i < byteData.length; i++) 
	        {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
    	
    	return sb.toString();
    }
    
}
