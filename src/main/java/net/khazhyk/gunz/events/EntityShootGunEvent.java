package net.khazhyk.gunz.events;
import net.khazhyk.gunz.guns.Gun;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;


public class EntityShootGunEvent extends EntityEvent implements Cancellable {
    private ItemStack gunItem;
    private Gun gunType;
    private boolean cancelled;
    //private Entity projectile;
    
    private static HandlerList handlers = new HandlerList();
    

    public EntityShootGunEvent(LivingEntity shooter,
                               ItemStack gunItem,
                               Gun gunType) {
        super(shooter);
        this.gunItem = gunItem;
        this.gunType = gunType;
        // TODO - Allow manipulation of the fired projectile
        //this.projectile = firedProjectile;  // Allows manipulation of the fired projectile;
        this.cancelled = false;
    }
    
    public ItemStack getItemStack() {
        return gunItem;
    }
    
    public Gun getGunType() {
        return gunType;
    }
    /*
    public Entity getProjectile() {
        return projectile;
    }*/

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
