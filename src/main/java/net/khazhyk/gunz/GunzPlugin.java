package net.khazhyk.gunz;

import net.khazhyk.gunz.guns.Gun;
import net.khazhyk.gunz.guns.SimpleGun;
import net.khazhyk.gunz.listeners.GunsListener;
import net.khazhyk.util.sound.SoundEff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public class GunzPlugin extends JavaPlugin {
    
    GunsListener gl;

    public void onEnable() {
        this.gl = new GunsListener(this);
        Gun chickenCannon = new SimpleGun("Chicken Cannon", Material.DIAMOND_HOE, EntityType.EGG, 100, 1500, 1);
        gl.addGun(chickenCannon);
        
        Gun grenadeLauncher = new SimpleGun("Grenade Launcher", Material.WOOD_SPADE, EntityType.SNOWBALL, 100, 240, 300);
        gl.addGun(grenadeLauncher);

        Gun teleportGun = new SimpleGun("Teleport Gun", Material.IRON_HOE, EntityType.ENDER_PEARL, 100, 60, 0);
        gl.addGun(teleportGun);
        
        Gun flameThrower = new Gun("Flamethrower", Material.GOLD_HOE, EntityType.SMALL_FIREBALL, 100, 600, 40, 40, 4,
                new SoundEff(Sound.FIRE, 5, 1));
        gl.addGun(flameThrower);
        
        this.getServer().getPluginManager().registerEvents(gl, this);
    }
    
    public void onDisable() {
    }
}
