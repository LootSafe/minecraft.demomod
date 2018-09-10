package com.lootsafe.mod.util.handlers;

import javax.json.JsonObject;

import com.lootsafe.mod.Items.ItemBase;
import com.lootsafe.mod.util.Reference;

import io.lootsafe.api.ServiceProvider;
import io.lootsafe.api.Requests.NodeHandler;
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
    	
        try {
            
        	JsonObject response = nh.postSpawnItem(playerAddress, item.getItemAddress());
            
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
    
}
