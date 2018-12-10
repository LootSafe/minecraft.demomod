package com.lootsafe.mod.util.network;

import javax.json.JsonObject;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;

import io.lootsafe.api.ServiceProvider;
import io.lootsafe.api.Requests.NodeHandler;

public class HandlerNetwork {
		
    private NodeHandler nh;
    private ServiceProvider sv;    
    
    public HandlerNetwork(){
    	
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
        	JsonObject response = nh.mintAssetRaw(playerAddress, uniqueItemAddress, 1);
        	
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
            e.printStackTrace();
            return false;
        } 
        
    }
    
}
