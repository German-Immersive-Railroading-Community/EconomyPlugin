package eu.girc.economy.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import eu.girc.economy.commands.MoneyCommand;
import eu.girc.economy.commands.ProfileCommand;
import eu.girc.economy.config.Config;
import eu.girc.economy.events.PlayerJoinListener;
import eu.girc.economy.utilities.ExtendedItemStack;
import eu.girc.economy.utilities.PlayerInventory;

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
		Bukkit.getPluginManager().registerEvents(ExtendedItemStack.getInstance(), this);
		Bukkit.getPluginManager().registerEvents(PlayerInventory.getInstance(), this);
	}

}
