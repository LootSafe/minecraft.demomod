package com.lootsafe.mod.commands;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.util.handlers.PlayerHandler;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CustomCommandWalletAddress implements ICommand {

	@Override
	public String getName() {
		return "commandregisterplayerwallet";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "Shows a wallet address if registered";
	}

	@Override
	public List<String> getAliases() {
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("wallet");
		return commandAliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer) sender;	
			
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Showing Wallet Address..."));
			
			if(args.length != 0){
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument"));
			}
			else{		
				
				String walletAddress = PlayerHandler.getInstance().getPlayerWalletAddress(player.getName());				
				
				if(walletAddress != null){
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.GREEN + walletAddress));
				}
				else{
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "You aren't registered."));
				}				
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
