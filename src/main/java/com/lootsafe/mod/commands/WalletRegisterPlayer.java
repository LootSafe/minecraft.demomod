package com.lootsafe.mod.commands;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Main;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class WalletRegisterPlayer implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender instanceof EntityPlayer)
		{			
			EntityPlayer player = (EntityPlayer) sender;	
			
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Register Wallet Address..."));
			
			if(args.length != 1)
			{
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument"));
			}
			else
			{					
				if(Main.proxy.isPlayerRegistered(player.getName()) == false)
				{
					Main.proxy.registerPlayerWallet(player.getName(), args[0]);
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.GREEN + "Registered!"));
				}
				else
				{
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Already Registered!"));
				}							
			}			
		}
	}
	
	@Override
	public String getName() 
	{
		return "commandregisterplayerwallet";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "Registers a players wallet";
	}

	@Override
	public List<String> getAliases() 
	{
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("register");
		return commandAliases;
	}

	/*
	 * Required and Redundant 
	 */
	
	@Override
	public int compareTo(ICommand arg0) { return 0; }
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) { return true; }

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) { return null; }

	@Override
	public boolean isUsernameIndex(String[] args, int index) { return false; }
	
}
