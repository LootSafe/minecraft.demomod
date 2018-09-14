package com.lootsafe.mod.util.handlers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.JsonObject;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.Items.ItemBase;

import io.lootsafe.api.Requests.NodeHandler;
import io.lootsafe.api.ServiceProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class NetworkHandler {
		
    private NodeHandler nh;
    private ServiceProvider sv;    
    
    public NetworkHandler(){
    	
        sv = new ServiceProvider.ServiceBuilder()
		        .withVersion(Reference.version)
		        .withHost(Reference.host)
		        .withPrivateKey(Reference.privateKey)
		        .withDebug(Reference.debug)
		        .build();
		
		sv.startService();
		
		if (sv.isWorking()) {
		    nh = sv.getNodeHandler();
		    nh.test();
		} else {
		    throw new RuntimeException("Could not start up the LootSafe Service! Is your config in order?");
		}	    	
		
    }
    
    public boolean GivePlayerItem(EntityPlayer player, String playerAddress, ItemBase item) {
    	
		player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Sending Player Item"));
    	
		/*
		 * Unique Item Addresses should be based off the base addresses and created fresh from the API
		 * For now a mock object is used in place of it. 
		 */
		
		String uniqueItemAddress = getUniqueAddress(item.getItemAddress());		
		
		/* See Above */		
		
        try {
            
        	//JsonObject response = nh.postSpawnItem(playerAddress, item.getItemAddress());
        	JsonObject response = nh.postSpawnItem(playerAddress, uniqueItemAddress);
        	
        	if (response.getInt("status") == 200) {
        		player.sendMessage(new TextComponentString("Check your online wallet to see your item!"));
        		return true;
        	}
        	else{
        		player.sendMessage(new TextComponentString("Error sending item to the online wallet"));
        		player.sendMessage(new TextComponentString(response.toString()));
        		return false;        		
        	}

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public String getUniqueAddress(String itemAddress){
    	return mockUniqueAddress(itemAddress);
    }
    
    public String mockUniqueAddress(String itemAddress){
    	
    	MessageDigest md;
        StringBuffer sb = new StringBuffer();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	
    	String itemAddressMD5 = dateFormat.format(date) + " " + itemAddress;
    	    	
    	sb.append("0x");
    	
		try {
			
			md = MessageDigest.getInstance("MD5");
	        md.update(itemAddressMD5.getBytes());
	        
	        byte byteData[] = md.digest();
	 
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	
    	return sb.toString();
    }
    
}
