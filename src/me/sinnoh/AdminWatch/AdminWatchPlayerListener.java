package me.sinnoh.AdminWatch;


import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AdminWatchPlayerListener implements Listener{
	public Map<Player, Long> cool = new HashMap<Player, Long>();

	public static AdminWatch plugin;
	public AdminWatchPlayerListener(AdminWatch nbp)
	{
		plugin = nbp;
	}
	
public AdminWatchPlayerListener(){
	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
}
	@EventHandler
	public void onPlayer(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
			if(player.hasPermission("adminwatch.use"))	
			{
				if(player.getItemInHand().getType().equals(Material.WATCH))
				{
					if(!player.hasPermission("adminwatch.cooldown"))
					{
						if(cool.containsKey(player))
						{
							if(System.currentTimeMillis() - cool.get(player) >= plugin.getConfig().getInt("AdminWatch.cooldown")*1000)
							{
								if(event.getAction() == Action.LEFT_CLICK_AIR)
								{
									player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.leftclick.time"));
									player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]"  + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.leftclick.message"));
									cool.put(player, System.currentTimeMillis());
								}
								if(event.getAction() == Action.RIGHT_CLICK_AIR)
								{
									player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.rightclick.time"));
									player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]" + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.rightclick.message"));
									cool.put(player, System.currentTimeMillis());
								}
							}
						}
						if(!cool.containsKey(player))
						{
							cool.put(player, System.currentTimeMillis() - plugin.getConfig().getInt("AdminWatch.cooldown")*1000);
							if(System.currentTimeMillis() - cool.get(player) >= plugin.getConfig().getInt("AdminWatch.cooldown")*1000)
							{
								if(event.getAction() == Action.LEFT_CLICK_AIR)
								{
									player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.leftclick.time"));
									player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]"  + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.leftclick.message"));
									cool.put(player, System.currentTimeMillis());
								}
								if(event.getAction() == Action.RIGHT_CLICK_AIR)
								{
									player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.rightclick.time"));
									player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]" + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.rightclick.message"));
									cool.put(player, System.currentTimeMillis());
								}
							}
						}
					}
					if(player.hasPermission("adminwatch.cooldown"))
					{
							if(event.getAction() == Action.LEFT_CLICK_AIR)
							{
								player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.leftclick.time"));
								player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]"  + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.leftclick.message"));
								cool.put(player, System.currentTimeMillis());
							}
							if(event.getAction() == Action.RIGHT_CLICK_AIR)
							{
								player.getWorld().setTime(plugin.getConfig().getInt("AdminWatch.rightclick.time"));
								player.sendMessage(ChatColor.GREEN + "[" + plugin.getConfig().getString("AdminWatch.prefix") + "]" + ChatColor.WHITE + plugin.getConfig().getString("AdminWatch.rightclick.message"));
								cool.put(player, System.currentTimeMillis());
							}
						}
					}
				}
			}
}
