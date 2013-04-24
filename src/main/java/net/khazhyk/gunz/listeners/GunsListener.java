package net.khazhyk.gunz.listeners;
import java.util.HashSet;
import java.util.Map;

import net.khazhyk.gunz.events.EntityShootGunEvent;
import net.khazhyk.gunz.guns.Gun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.google.common.collect.Maps;


public class GunsListener implements Listener {
    private Map<Material,Gun> guns;
    
    private JavaPlugin plugin;
    private PluginManager pluginManager;
    
    public GunsListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.guns = Maps.newHashMap();
    }
    
    public void addGun(Gun gun) {
        this.guns.put(gun.getTool(), gun);
    }
    
    @EventHandler
    public void onGunFired(PlayerInteractEvent e) {
        // Only care about right clicking
        if (e.getAction() == Action.RIGHT_CLICK_AIR
                || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material mat = e.getMaterial();
            // Check that we have a material in hand
            if (mat != null) {
                Gun gun = guns.get(mat);
                // Check that it's a registered gun
                if (gun != null) {
                    e.setCancelled(true); // Prevent default interaction
                    fireGun(e.getPlayer(), e.getItem(), gun);
                }
            }
        }
    }
    
    private void fireGun(Player p, ItemStack gunItem, Gun gun) {
        Projectile proj = p.launchProjectile(Egg.class);
        proj.
        ((Egg)proj).setBounce(true);
        //proj.setVelocity(proj.getVelocity().multiply(5.0));
        EntityShootGunEvent esge = new EntityShootGunEvent(p,gunItem, gun, proj);
        pluginManager.callEvent(esge);
        if (!esge.isCancelled()) {
            plugin.getServer().broadcastMessage("Player " + p.getName() + " fired a gun: " + gun.getName() +"!");
        }
    }
}
