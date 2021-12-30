package economy.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import economy.commands.MoneyCommand;
import economy.commands.ProfileCommand;
import economy.config.Config;
import economy.events.PlayerJoinListener;
import economy.events.PlayerQuitListener;
import serversystem.handler.InventoryHandler;

public class Economy extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new Config();
		registerCommands();
		registerEvents();
		//EconomyPlayer.getPlayerByName("Der_Zauberer").addLicense(new EconomyLicense(Bukkit.getPlayer("Der_Zauberer").getUniqueId().toString(), LicenseType.TICKET, "2021.12.30"));
	}
	
	private void registerCommands() {
		getCommand("money").setExecutor(new MoneyCommand());
		getCommand("profile").setExecutor(new ProfileCommand());
	}
	
	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
	}

}
