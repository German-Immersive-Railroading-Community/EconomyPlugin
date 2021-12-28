package economy.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import economy.objects.EconomyPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		EconomyPlayer economyPlayer = EconomyPlayer.getPlayerByUUID(event.getPlayer().getUniqueId().toString());
		if (economyPlayer != null) {
			economyPlayer.setOnline(event.getPlayer());
		} else {
			new EconomyPlayer(event.getPlayer());
		}
		
	}
	
}
