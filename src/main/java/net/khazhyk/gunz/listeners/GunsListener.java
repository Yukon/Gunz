package net.khazhyk.gunz.listeners;
import java.util.HashSet;
import java.util.Map;

import net.khazhyk.gunz.events.EntityShootGunEvent;
import net.khazhyk.gunz.guns.Gun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


public class GunsListener implements Listener {
    private Map<Material,Gun> guns;
    private HashSet<Projectile> bullets;
    
    private JavaPlugin plugin;
    private PluginManager pluginManager;
    
    public GunsListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.guns = Maps.newHashMap();
        this.bullets = Sets.newHashSet();
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
                final Gun gun = guns.get(mat);
                // Check that it's a registered gun
                if (gun != null) {
                    e.setCancelled(true); // Prevent default interaction
                    double shotsPerEvent = ((gun.getFireRate() / 60.0) / 5.0); // getFireRate is RPM, devide by 60 to get RPS, 5 events/second
                    double ticksPerShot = 1.0 / (shotsPerEvent / 4.0);
                    // 1 event is 4 ticks
                    double delay = 0;
                    // FIXME - Implement fire rate limiting for firerates slower than 1 per event, also ratelimit events
                    final Player pl = e.getPlayer();
                    final ItemStack item = e.getItem();
                    for (int fired = 0; fired < shotsPerEvent; fired++, delay += ticksPerShot) {
                        int intdelay = (int)delay;
                        if (intdelay > 0) {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            fireGun(pl,item,gun);
                                        }
                            }, intdelay);
                        } else {
                            fireGun(pl, item, gun);
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onBulletHit(ProjectileHitEvent e) {
        
        Projectile proj = e.getEntity();
        if (bullets.contains(proj)) {
            if (proj.getType() == EntityType.SNOWBALL) {
                proj.getWorld().createExplosion(proj.getLocation(), 3);
            }
            bullets.remove(proj);
        }
    }
    
    @EventHandler
    public void onBulletHit(EntityDamageByEntityEvent e) {
        // FIXME - This should do damage according to gun
        if (e.getDamager().getType() == EntityType.EGG) {
            e.setDamage(20);
        }
    }
    
    private void fireGun(Player p, ItemStack gunItem, Gun gun) {
        // This fires an event for every bullet - this might not be ideal
        World w = p.getWorld();
        Location l = p.getEyeLocation();
        Vector d = l.getDirection(); // Direction we're looking
        // FIXME - Need to take into account player speed so we don't shoot ourselves
        Location finalloc = l.clone().add(d);        
        Vector vel = d.clone().multiply(gun.getVelocity() / 20.0); // Velocity is in blocks/tick
        // FIXME - Using Bukkit limits us to spawning without velocity, and limits what type of
        // Objects we can spawn, use NMS here
        Projectile proj = (Projectile) w.spawnEntity(finalloc, gun.getBullet());
        this.bullets.add(proj);
        proj.setVelocity(vel);
        proj.setShooter(p);
        EntityShootGunEvent esge = new EntityShootGunEvent(p,gunItem, gun, proj);
        pluginManager.callEvent(esge);
        if (!esge.isCancelled()) {
            w.playSound(finalloc, Sound.ENDERDRAGON_HIT, 1, 50);
        } else {
            proj.remove();
        }
    }
}
