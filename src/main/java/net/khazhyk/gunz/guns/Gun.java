package net.khazhyk.gunz.guns;

import org.bukkit.Material;

public class Gun {
    protected Material tool;    // The tool/item you use to fire this gun.
    protected int velocity;     // The speed of bullets fired by this gun in meters/second
    protected int fireRate;     // How fast this gun fires bullets in shots/minute
    protected String name;      // Displayed name of this weapon/item
    
    public Gun(String name, Material tool, int velocity, int fireRate) {
        this.name = name;
        this.tool = tool;
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
}
