package com.lootsafe.mod.commands.player;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Reference;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class BlankChat implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender instanceof EntityPlayer)
		{			
			EntityPlayer player = (EntityPlayer) sender;				
			player.sendMessage(new TextComponentString(TextFormatting.BOLD + Reference.CLEAR_SCREEN));						
		}
	}
	
	@Override
	public String getName()
	{
		return "clearscreen";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "Clears the screen";
	}

	@Override
	public List<String> getAliases()
	{
		List<String> commandAliases = new ArrayList<String>();
		commandAliases.add("blank");
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
