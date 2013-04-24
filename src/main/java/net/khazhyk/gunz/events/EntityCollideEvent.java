package net.khazhyk.gunz.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public class EntityCollideEvent extends EntityEvent {
    private static HandlerList handlers = new HandlerList();
    
    public EntityCollideEvent(Entity what) {
        super(what);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
