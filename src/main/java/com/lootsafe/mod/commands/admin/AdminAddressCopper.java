package com.lootsafe.mod.commands.admin;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.util.handlers.HandlerLootDispenser;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class AdminAddressCopper implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender instanceof EntityPlayer)
		{			
			EntityPlayer player = (EntityPlayer) sender;				
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + "Replacing Copper Coin Address..."));
			
			if(args.length == 1)
			{				
				HandlerLootDispenser.getInstance().setCopperAddress(args[0]);
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.GREEN + "Copper Coin Set to: " + args[0]));			
			}
			else
			{				
				player.sendMessage(new TextComponentString(TextFormatting.BOLD + " | " + TextFormatting.RED + "Please use command with only 1 argument"));
			}			
		}		
	}
	
	@Override
	public String getName()
	{
		return "setcopperaddress";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "Registers the copper coin...";
	}

	@Override
	public List<String> getAliases()
	{
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("registercopper");
		return commandAliases;
	}
	
	public boolean canCommandSenderUse(MinecraftServer server, ICommandSender sender)
	{ 		
		if(sender.getName() == null)
		{
			return false;
		}
		
		if(sender.canUseCommand(1, getName()) == false)
		{
			return false;
		}
		
		return true;
	}
	
	/*
	 * Required and Redundant 
	 */
	
	@Override
	public int compareTo(ICommand arg0) { return 0; }
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) { return null; }

	@Override
	public boolean isUsernameIndex(String[] args, int index) { return false; }

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) { return canCommandSenderUse(server,sender); }
	
}
