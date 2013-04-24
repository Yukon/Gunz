package net.khazhyk.gunz;

import net.khazhyk.gunz.guns.Gun;
import net.khazhyk.gunz.listeners.GunsListener;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class GunzPlugin extends JavaPlugin {
    
    GunsListener gl;

    public void onEnable() {
        this.gl = new GunsListener(this);
        Gun ak47 = new Gun("ak47", Material.DIAMOND_HOE, 300, 1800);
        gl.addGun(ak47);
        
        this.getServer().getPluginManager().registerEvents(gl, this);
    }
    
    public void onDisable() {
    }
}
