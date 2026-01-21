package com.eni.seamless.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModEvents {

    public static void onServerTick(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            tickPlayer(player);
        }
    }

    private static void tickPlayer(ServerPlayer player) {
        ServerLevel level = (ServerLevel) player.level();
        double y = player.getY();

        // 1.21.1 Teleportation Logic

        // Overworld -> Nether (Going Down)
        if (level.dimension().equals(Level.OVERWORLD) && y < -60) {
            ServerLevel nether = level.getServer().getLevel(Level.NETHER);
            if (nether != null) {
                // Fabric friendly teleport
                player.teleport(new net.minecraft.world.level.portal.TeleportTransition(
                        nether,
                        new Vec3(player.getX(), 127, player.getZ()),
                        Vec3.ZERO,
                        player.getYRot(),
                        player.getXRot(),
                        net.minecraft.world.level.portal.TeleportTransition.DO_NOTHING));
            }
        }

        // Nether -> Overworld (Going Up)
        else if (level.dimension().equals(Level.NETHER) && y > 128) {
            ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
            if (overworld != null) {
                player.teleport(new net.minecraft.world.level.portal.TeleportTransition(
                        overworld,
                        new Vec3(player.getX(), -59, player.getZ()),
                        Vec3.ZERO,
                        player.getYRot(),
                        player.getXRot(),
                        net.minecraft.world.level.portal.TeleportTransition.DO_NOTHING));
            }
        }
    }
}
