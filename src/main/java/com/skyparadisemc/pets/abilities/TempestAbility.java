package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import com.skyparadisemc.pets.plugin.PetsPlugin;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class TempestAbility implements Ability {

    private @Inject PetService petService;

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player pl = event.getPlayer();

        Pet pet = petService.getPet(pl);

        for (Entity e : pet.getEntity().getNearbyEntities(5, 5,5)) {

            if (!(e instanceof Player))
                continue;

            if (e == pl)
                continue;


            ((Player)e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*10, 2));
            e.sendMessage(pl.getName() + "'s " + pet.getCustomName() + " has used the: " + getName() + " special!");
            ((Player) e).damage(0);
            createHelix((Player) e);
            break;
        }
    }

    public void createHelix(Player player) {

        Location loc = player.getLocation();
        int radius = 1;
        for(double y = 0; y <= 5; y+=0.01) {
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z), 0, 0, 0,0, 1);
            for(Player online : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    @Override
    public String getName() {
        return "tempest";
    }

    @Override
    public long getCooldown() {
        return TimeUnit.MINUTES.toMillis(5);
    }
}
