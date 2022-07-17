package eu.girc.economy.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import eu.girc.economy.utilities.EconomyPlayer;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(Player player) {
	final EconomyPlayer economyPlayer = EconomyPlayer.getPlayerByBucketPlayer(player);
	if (EconomyPlayer.playerExists(economyPlayer)) {
	    EconomyPlayer.removePlayer(economyPlayer); 
	    economyPlayer.setOffline();
	}
    }
    
}
