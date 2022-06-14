package eu.girc.economy.utilities;

import eu.girc.economy.config.Config;

public class EconomyLicense {
	
	public enum LicenseType {TF_LICENSE, FDL_LICENSE, ZB_LICENSE, TICKET};
	
	private static int counter = Config.getLicenseCounter();
	
	private final int id;
	private final String player;
	private final LicenseType type;
	private final String validSince;
	
	public EconomyLicense(int id, String player, LicenseType type, String validSince) {
		this.id = id;
		this.player = player;
		this.type = type;
		this.validSince = validSince;
		Config.setLicense(this);
	}
	
	public EconomyLicense(String player, LicenseType type, String validSince) {
		this(counter, player, type, validSince);
		counter++;
		Config.setLicenseCounter(counter);
	}
	
	public int getId() {
		return id;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public LicenseType getType() {
		return type;
	}
	
	public String getValidSince() {
		return validSince;
	}
	
	public void removeLicense() {
		EconomyPlayer.getPlayerByUUID(player).removeLicense(this);
		Config.removeLicense(id);
	}

}
