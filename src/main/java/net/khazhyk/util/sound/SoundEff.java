package net.khazhyk.util.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundEff {
    private Sound sound;
    private float volume;
    private float pitch;
    
    public SoundEff(Sound s, float v, float p) {
        this.sound = s;
        this.volume = v;
        this.pitch = p;
    }
    
    public void playSound(World w, Location l) {
        w.playSound(l, this.sound, this.volume, this.pitch);
    }
    
    public void playSound(Player p, Location l) {
        p.playSound(l, this.sound, this.volume, this.pitch);
    }
}
