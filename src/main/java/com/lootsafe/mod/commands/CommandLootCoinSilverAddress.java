package com.lootsafe.mod.commands;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.util.Reference;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandLootCoinSilverAddress implements ICommand {

	@Override
	public String getName() {
		return "commandregisterlootcoinsilveraddress";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "Registers the silver coin...";
	}

	@Override
	public List<String> getAliases() {
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("registersilver");
		return commandAliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer) sender;				
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Replacing Silver Coin Address..." + Reference.lootcoin_silver_address));
			
			if(args.length == 1){				
				Reference.lootcoin_silver_address = args[1];
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.GREEN + "Silver Coin Set to: " + args[0]));			
			}
			else{				
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument"));
			}			
		}
	}

	/*
	 * Extra Stuffs... 
	 */
	
	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
