import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class spigpass extends JavaPlugin implements Listener {

    private static final String PASSWORD = "meow123"; // Change this!
    private final Set<String> authenticatedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "This server is password protected. Use /password <password> to gain access.");
        player.setInvulnerable(true);
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("password") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1 && args[0].equals(PASSWORD)) {
                authenticatedPlayers.add(player.getName());
                player.sendMessage(ChatColor.GREEN + "Access granted!");
                player.setInvulnerable(false);
                player.setAllowFlight(false);
                return true;
            } else {
                player.kickPlayer(ChatColor.RED + "Incorrect password!");
                return false;
            }
        }
        return false;
    }
}
