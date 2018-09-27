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

public class WalletShowLoot implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sender;
			
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Checking Loot..."));
			
			if(args.length == 0)
			{				
				if(Main.proxy.isPlayerRegistered(player.getName()))
				{				
					for(String item : Main.proxy.getPlayerTokenizedItemList(player.getName()))
					{
						player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.WHITE + item));												
					}
				}
				else
				{
					player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please register address to account."));
				}					
			}
			else
			{
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument."));
			}			
		}
	}
	
	@Override
	public String getName()
	{
		return "showwalletitems";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "Shows a list of players items.";
	}

	@Override
	public List<String> getAliases() 
	{
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("loot");
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
