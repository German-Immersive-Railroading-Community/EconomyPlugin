package economy.menus;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import economy.objects.EconomyLicense;
import economy.objects.EconomyPlayer;
import serversystem.utilities.PlayerInventory;

public class ProfileMenu extends PlayerInventory {

	public ProfileMenu(Player player) {
		super(player, 18, "Profile");
		setItemOption(ItemOption.FIXED);
		setItem(0, createItem(ChatColor.BOLD + "Passport", Material.BOOK, getInfoLore(player)));
		ArrayList<EconomyLicense> licenses = EconomyPlayer.getPlayerByBucketPlayer(player).getLicenses();
		for (int i = 0; i < licenses.size(); i++) {
			setItem(i + 1, createItem(ChatColor.BOLD + licenses.get(i).getType().toString().replace('_', ' '), Material.PAPER, getLicenseLore(licenses.get(i))));
		}
		open();
	}
	
	public List<String> getInfoLore(Player player) {
		List<String> string = new ArrayList<>();
		string.add("");
		string.add(ChatColor.DARK_GRAY + "ID");
		string.add(ChatColor.WHITE + player.getUniqueId().toString());
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Name");
		string.add(ChatColor.WHITE + player.getName());
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Member since");
		string.add(ChatColor.WHITE + "" + EconomyPlayer.getPlayerByBucketPlayer(player).getDate());
		string.add("");
		return string;
	}
	
	public List<String> getLicenseLore(EconomyLicense license) {
		List<String> string = new ArrayList<>();
		string.add("");
		string.add(ChatColor.DARK_GRAY + "ID");
		string.add(ChatColor.WHITE + Integer.toString(license.getId()));
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Player");
		string.add(ChatColor.WHITE + EconomyPlayer.getPlayerByUUID(license.getPlayer()).getName());
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Type");
		string.add(ChatColor.WHITE + license.getType().toString().replace('_', ' '));
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Valid since");
		string.add(ChatColor.WHITE + "" + license.getValidSince());
		string.add("");
		return string;
	}

}
