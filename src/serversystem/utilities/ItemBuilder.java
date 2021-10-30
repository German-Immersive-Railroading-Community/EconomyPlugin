package serversystem.utilities;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class ItemBuilder {
	
	private String displayname;
	private Material material;
	private List<String> lore;
	private ItemStack itemstack;
	
	public ItemBuilder() {
		displayname = "Example Soward";
		material = Material.WOOD_SWORD;
	}
	
	public ItemBuilder(Material material) {
		displayname = "Example Soward";
		this.material = material;
	}
	
	public ItemBuilder(String displayname) {
		this.displayname = displayname;
		material = Material.WOOD_SWORD;
	}
	
	public ItemBuilder(String displayname, Material material) {
		this.displayname = displayname;
		this.material = material;
	}
	
	public void setDisplayName(String displayname) {
		this.displayname = displayname;
	}
	
	public String getDisplayName() {
		return displayname;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
	public List<String> getLore() {
		return lore;
	}
	
	public void addPotionMeta(Color color, PotionEffect potioneffect) {
		if(itemstack != null && material == Material.POTION) {
			PotionMeta potionmeta = (PotionMeta) itemstack.getItemMeta();
			potionmeta.setColor(color);
			potionmeta.addCustomEffect(potioneffect, true);
			itemstack.setItemMeta(potionmeta);
		}
	}
	
	public void addPlayerSkullMeta(Player player) {
		if(itemstack != null && material == Material.SKULL) {
			SkullMeta skullmeta = (SkullMeta) itemstack.getItemMeta();
			skullmeta.setOwningPlayer(player);
			itemstack.setItemMeta(skullmeta);
		}
	}
	
	public ItemStack buildItem() {
		itemstack = new ItemStack(material);
		buildItem(itemstack);
		return itemstack;
	}
	
	public void buildItem(ItemStack itemstack) {
		ItemMeta itemmeta = itemstack.getItemMeta();
		itemmeta.setDisplayName(ChatColor.RESET + displayname);
		if(lore != null) {
			itemmeta.setLore(lore);
		}
		itemstack.setItemMeta(itemmeta);
	}
	
	public ItemMeta getItemMeta() {
		if(itemstack == null) {
			return buildItem().getItemMeta();
		}
		return itemstack.getItemMeta();
	}
	
	public ItemStack getItemStack() {
		if(itemstack == null) {
			return buildItem();
		}
		return itemstack;
	}

}
