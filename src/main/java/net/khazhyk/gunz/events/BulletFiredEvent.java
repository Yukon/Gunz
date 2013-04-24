package net.khazhyk.gunz.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class BulletFiredEvent extends ProjectileLaunchEvent {

    public BulletFiredEvent(Projectile projectile) {
        super(projectile);
    }

    public LivingEntity getShooter() {
        return ((Projectile)this.entity).getShooter();
    }
}
