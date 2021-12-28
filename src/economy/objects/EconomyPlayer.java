package economy.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import economy.config.Config;

public class EconomyPlayer {
	
	public static ArrayList<EconomyPlayer> players = new ArrayList<>();
	
	private Player player;
	private String name;
	private UUID uuid;
	private boolean online;
	private long balance;
	private String date;
	
	public EconomyPlayer(Player player) {
		players.add(this);
		setOnline(player);
	}
	
	public EconomyPlayer(String name, UUID uuid) {
		players.add(this);
		this.name = name;
		this.uuid = uuid;
		setOffline();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getName() {
		return name;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public void setOnline(Player player) {
		this.player = player;
		this.name = player.getName();
		this.uuid = player.getUniqueId();
		this.online = true;
		Config.getPlayerData(this);	
		Config.setPlayerData(this);
	}
	
	public void setOffline() {
		this.player = null;
		this.online = false;
		Config.getPlayerData(this);	
		Config.setPlayerData(this);
	}
	
	public boolean isOnline() {
		return online;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
		Config.setPlayerBalance(this);
	}
	
	public long getBalance() {
		return balance;
	}
	
	public void tranferMoney(EconomyPlayer target, long amount) {
		this.balance -= amount;
		target.balance += amount;
		Config.setPlayerBalance(this);
		Config.setPlayerBalance(target);
	}
	
	public void setDate(String date) {
		this.date = date;
		Config.setPlayerDate(this);
	}
	
	public String getDate() {
		return date;
	}
	
	public static EconomyPlayer getPlayerByBucketPlayer(Player player) {
		for (EconomyPlayer economyPlayer : players) {
			if (economyPlayer.getPlayer() == player) {
				return economyPlayer;
			}
		}
		return null;
	}
	
	public static EconomyPlayer getPlayerByName(String name) {
		for (EconomyPlayer economyPlayer : players) {
			if (economyPlayer.getName().equals(name)) {
				return economyPlayer;
			}
		}
		return null;
	}
	
	public static EconomyPlayer getPlayerByUUID(String uuid) {
		for (EconomyPlayer economyPlayer : players) {
			if (economyPlayer.getUUID().toString().equals(uuid)) {
				return economyPlayer;
			}
		}
		return null;
	}
	
	public static ArrayList<EconomyPlayer> getPlayers() {
		return players;
	}
	
	public static ArrayList<String> getPlayerNames() {
		ArrayList<String> name = new ArrayList<>();
		players.forEach((player) -> name.add(player.getName()));
		return name;
	}

}
