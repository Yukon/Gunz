package net.khazhyk.gunz.events;
import net.khazhyk.gunz.guns.Gun;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;


public class EntityShootGunEvent extends EntityEvent implements Cancellable {
    private ItemStack gunItem;
    private Gun gunType;
    private Projectile projectile;
    private boolean cancelled;
    
    private static HandlerList handlers = new HandlerList();
    

    public EntityShootGunEvent(LivingEntity shooter,
                               ItemStack gunItem,
                               Gun gunType,
                               Projectile projectile) {
        super(shooter);
        this.gunItem = gunItem;
        this.gunType = gunType;
        this.projectile = projectile;
        this.cancelled = false;
    }
    
    public ItemStack getItemStack() {
        return gunItem;
    }
    
    public Gun getGunType() {
        return gunType;
    }
    
    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
