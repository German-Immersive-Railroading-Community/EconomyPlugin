package economy.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import economy.objects.EconomyPlayer;

public class PlayerQuitListener implements Listener{

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		EconomyPlayer.getPlayerByUUID(event.getPlayer().getUniqueId().toString()).setOffline();
	}
	
}
