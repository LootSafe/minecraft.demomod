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

public class WalletWalletAddress implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender instanceof EntityPlayer)
		{			
			EntityPlayer player = (EntityPlayer) sender;	
			
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Showing Wallet Status..."));
			
			if(args.length != 0)
			{
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument."));
			}
			else
			{						
				String walletAddress = Main.proxy.getPlayerWalletAddress(player.getName());				
				
				if(walletAddress != null)
				{
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | Registered to address @ " + TextFormatting.GREEN + walletAddress));
				}
				else
				{
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "You aren't registered."));
				}	
				
			}
			
		}
		
	}
	
	@Override
	public String getName() 
	{
		return "walletstatus";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "Shows wallet status.";
	}

	@Override
	public List<String> getAliases()
	{
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("wallet");
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
