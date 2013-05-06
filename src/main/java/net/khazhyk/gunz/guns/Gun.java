package net.khazhyk.gunz.guns;

import net.khazhyk.util.sound.SoundEff;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class Gun {
    protected Material tool;    // The tool/item you use to fire this gun.
    protected EntityType firedEntity;
    protected int velocity;     // The speed of bullets fired by this gun in meters/second MAX 95, any higher will have visual glitches
    protected int fireRate;     // How fast this gun fires bullets in shots/minute
    protected int reloadTicks;  // How many ticks it takes to reload (20 tps)
    protected int magSize;
    protected int damage;       // How much damage each bullet does
    protected String name;      // Displayed name of this weapon/item
    protected SoundEff shotSound;   // The sound to play when firing this gun
    
    public Gun(String name,
               Material tool,
               EntityType firedEntity,
               int velocity,
               int fireRate,
               int damage,
               int magSize,
               int reloadTicks,
               SoundEff shotSound) {
        this.name = name;
        this.tool = tool;
        this.firedEntity = firedEntity;
        this.velocity = velocity;
        this.fireRate = fireRate;
        this.reloadTicks = reloadTicks;
        this.magSize = magSize;
        this.damage = damage;
        this.shotSound = shotSound;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Material getTool() {
        return this.tool;
    }
    
    public int getVelocity() {
        return this.velocity;
    }
    
    public EntityType getBullet() {
        return this.firedEntity;
    }
    
    public int getFireRate() {
        return this.fireRate;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public void playSound(World w, Location l) {
        this.shotSound.playSound(w, l);
    }
}
