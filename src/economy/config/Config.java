package economy.config;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import economy.objects.EconomyPlayer;

public class Config {
	
	private static File file = new File("plugins/Economy", "config.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public Config() {
		if(config.get("Bank.basicincome") == null) {
			config.set("Bank.basicincome", 0);
		}
		createEconomyPlayers();
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
	
	public static void setPlayerData(EconomyPlayer player) {
		String path = "Player." + player.getUUID() + ".";
		if (config.get(path + "name") == null) {
			LocalDateTime date = LocalDateTime.now();
			config.set(path + "name", player.getName());
			String string[] = new String[3];
			string[0] = Integer.toString(date.getYear());
			string[1] = Integer.toString(date.getMonthValue());
			string[2] = Integer.toString(date.getDayOfMonth());
			for (int i = 1; i < string.length; i++) {
				if (string[i].length() == 1) {
					string[i] = "0" + string[i];
				}
			}
			String dateString = string[0] + "." + string[1] + "." + string[2];
			player.setDate(dateString);
			config.set(path + "date", dateString);
		}
		config.set(path + "name", player.getName());
		config.set(path + "balance", player.getBalance());
		saveConfig();
	}
	
	public static void setPlayerBalance(EconomyPlayer player) {
		if (config.get("Player." + player.getUUID() + ".name") != null) {
			config.set("Player." + player.getUUID() + ".balance", player.getBalance());
			saveConfig();
		} else {
			setPlayerData(player);
		}
	}
	
	public static void setPlayerDate(EconomyPlayer player) {
		if (config.get("Player." + player.getUUID() + ".name") != null) {
			config.set("Player." + player.getUUID() + ".date", player.getDate());
			saveConfig();
		} else {
			setPlayerData(player);
		}
	}
	
	public static void getPlayerData(EconomyPlayer player) {
		String path = "Player." + player.getUUID() + ".";
		if (config.get(path + "name") == null) {
			LocalDateTime date = LocalDateTime.now();
			config.set(path + "date.year", date.getYear());
			config.set(path + "date.month", date.getMonthValue());
			config.set(path + "date.day", date.getDayOfMonth());
		}
		player.setDate(config.getString(path + "date"));
		player.setBalance(config.getLong(path + "balance"));
	}
	
	public static void createEconomyPlayers() {
		for (String uuid : getPlayersByUUID()) {
			String name = config.getString("Player." + uuid + ".name");
			if (Bukkit.getPlayer(name) != null) {
				new EconomyPlayer(Bukkit.getPlayer(name));
			} else if (Bukkit.getOfflinePlayer(UUID.fromString(uuid)) != null) {
				new EconomyPlayer(name, UUID.fromString(uuid));
			}
		}
	}
	
	public static List<String> getPlayers() {
		List<String> players = new ArrayList<>();
		if (getSection("Player", false) != null) {
			for(String key : getSection("Player", false)) {
				players.add(config.getString("Player." + key + ".name"));
			}
		}
		return players;
	}
	
	public static List<String> getPlayersByUUID() {
		List<String> players = new ArrayList<>();
		if (getSection("Player", false) != null) {
			getSection("Player", false).forEach((key) -> players.add(key));
		}
		return players;
	}
	
	public static void saveConfig() {
		try {
			config.save(file);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}