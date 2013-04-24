package net.khazhyk.gunz;

import net.khazhyk.gunz.guns.Gun;
import net.khazhyk.gunz.listeners.GunsListener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public class GunzPlugin extends JavaPlugin {
    
    GunsListener gl;

    public void onEnable() {
        this.gl = new GunsListener(this);
        Gun chickenCannon = new Gun("Chicken Cannon", Material.DIAMOND_HOE, EntityType.EGG, 100, 1500);
        gl.addGun(chickenCannon);
        
        Gun grenadeLauncher = new Gun("Grenade Launcher", Material.WOOD_SPADE, EntityType.SNOWBALL, 100, 240);
        gl.addGun(grenadeLauncher);

        Gun teleportGun = new Gun("Teleport Gun", Material.IRON_HOE, EntityType.ENDER_PEARL, 100, 1500);
        gl.addGun(teleportGun);
        
        this.getServer().getPluginManager().registerEvents(gl, this);
    }
    
    public void onDisable() {
    }
}
