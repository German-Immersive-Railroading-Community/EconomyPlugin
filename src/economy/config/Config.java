package economy.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Config {
	
	private static File file = new File("plugins/Economy", "config.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public Config() {
		if(config.get("Bank.basicincome") == null) {
			config.set("Bank.basicincome", 0);
		}
		saveConfig();
	}
	
	public static ArrayList<String> getSection(String section, boolean keys) {
		try {
			ArrayList<String> list = new ArrayList<>();
			for (String key : config.getConfigurationSection(section).getKeys(keys)) {
				list.add(key);
			}
			return list;
		} catch (NullPointerException exception) {
			return null;
		}
	}
	
	public static void setBasicIncome(long value) {
		config.set("Bank.basicincome", value);
		saveConfig();
	}
	
	public static long getBasicIncome() {
		return config.getLong("Bank.basicincome");
	}
	
	public static void addPlayer(Player player) {
		if(!doesPlayerExist(player)) {
			config.set("Player." + player.getUniqueId() + ".name", player.getName());
			config.set("Player." + player.getUniqueId() + ".balance", config.getLong("Bank.basicincome"));
		}
		config.set("Player." + player.getUniqueId() + ".name", player.getName());
		saveConfig();
	}
	
	public static boolean doesPlayerExist(Player player) {
		return (config.get("Player." + player.getUniqueId() + ".name") != null);
	}
	
	public static void setPlayerBalance(Player player, long balance) {
		config.set("Player." + player.getUniqueId() + ".balance", balance);
		saveConfig();
	}
	
	public static long getPlayerBalance(Player player) {
		return config.getLong("Player." + player.getUniqueId() + ".balance");
	}
	
	public static List<String> getPlayers() {
		List<String> players = new ArrayList<>();
		for(String key : getSection("Player", false)) {
			players.add(config.getString("Player." + key + ".name"));
		}
		return players;
	}
	
	public static void saveConfig() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
