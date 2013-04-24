package net.khazhyk.gunz.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;

public class BulletHitEvent extends ProjectileHitEvent {

    public BulletHitEvent(Projectile projectile) {
        super(projectile);
    }

    public LivingEntity getShooter() {
        return ((Projectile)this.entity).getShooter();
    }
}
