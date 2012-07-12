package me.sinnoh.AdminWatch;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminWatch extends JavaPlugin
{
	
	public void loadConfiguration()
	{
		File f = new File("plugins/AdminWatch/config.yml");
		if(!f.exists())
		{
		System.out.println("[AdminWatch] No config file found, creating one..");
		}
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	Logger log = Logger.getLogger("Minecraft");
		
	private final AdminWatchPlayerListener PlayerListener = new AdminWatchPlayerListener(this);
	
	
	@Override
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[AdminWatch] version " + pdfFile.getVersion() + " disabled" );
	}

	@Override
	public void onEnable() 
	{
		loadConfiguration();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(PlayerListener, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[AdminWatch] version " + pdfFile.getVersion() + " enabled" );
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(args.length == 1)
		{
		if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(command.getName().equalsIgnoreCase("adminwatch"))
				{
					if(player.hasPermission("adminwatch.admin"))
					{
						if(args[0].equalsIgnoreCase("reload"))
						{
							this.reloadConfig();
							player.sendMessage(ChatColor.GREEN + "[" +  this.getConfig().getString("AdminWatch.prefix") +  "]" + ChatColor.WHITE + "Reload completed!");
							return true;
						}
					}
				}
			}
		if(!(sender instanceof Player))
		{
			if(command.getName().equalsIgnoreCase("adminwatch"))
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					this.reloadConfig();
					System.out.print("[" + this.getConfig().getString("AdminWatch.prefix") + "]Reload completed!");
					return true;
				}
			}
		}
		}
		return false;
	}
	
}
