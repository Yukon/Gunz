package net.khazhyk.gunz.guns;

import net.khazhyk.util.sound.SoundEff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;

public class SimpleGun extends Gun {

    public SimpleGun(String name, Material tool, EntityType firedEntity,
            int velocity, int fireRate, int damage) {
        super(name, tool, firedEntity, velocity, fireRate, damage, 15,
                40, new SoundEff(Sound.ENDERDRAGON_HIT, 1, 50));
        // TODO Auto-generated constructor stub
    }

}
