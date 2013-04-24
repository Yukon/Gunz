package net.khazhyk.gunz.guns;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Gun {
    protected Material tool;    // The tool/item you use to fire this gun.
    protected EntityType firedEntity;
    protected int velocity;     // The speed of bullets fired by this gun in meters/second
    protected int fireRate;     // How fast this gun fires bullets in shots/minute
    protected String name;      // Displayed name of this weapon/item
    
    public Gun(String name, Material tool, EntityType firedEntity, int velocity, int fireRate) {
        this.name = name;
        this.tool = tool;
        this.firedEntity = firedEntity;
        this.velocity = velocity;
        this.fireRate = fireRate;
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
}
