/*
 *      ____        _ _     _                      _    _ _   _ _ _ _   _
 *     |  _ \      (_) |   | |                    | |  | | | (_) (_) | (_)
 *     | |_) |_   _ _| | __| | ___ _ __ ___ ______| |  | | |_ _| |_| |_ _  ___  ___
 *     |  _ <| | | | | |/ _` |/ _ \ '__/ __|______| |  | | __| | | | __| |/ _ \/ __|
 *     | |_) | |_| | | | (_| |  __/ |  \__ \      | |__| | |_| | | | |_| |  __/\__ \
 *     |____/ \__,_|_|_|\__,_|\___|_|  |___/       \____/ \__|_|_|_|\__|_|\___||___/
 *
 *    Builder's Utilities is a collection of a lot of tiny features that help with building.
 *                          Copyright (C) 2021 Arcaniax
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.arcaniax.buildersutilities.listeners;

import net.arcaniax.buildersutilities.BuildersUtilities;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.TrapDoor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IronTrapdoorListener implements Listener {

    public static Set<UUID> ironTrapdoorIds;

    public IronTrapdoorListener() {
        ironTrapdoorIds = new HashSet<>();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ironTrapDoorInteract(final PlayerInteractEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (ironTrapdoorIds.contains(e.getPlayer().getUniqueId())) {
            return;
        }
        if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (!e.getPlayer().hasPermission("builders.util.trapdoor")) {
            return;
        }
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!e.getClickedBlock().getType().equals(Material.IRON_TRAPDOOR)) {
            return;
        }
        if (e.getPlayer().isSneaking()) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(BuildersUtilities.getInstance(), () -> {
            Block b = e.getClickedBlock();
            BlockState state = b.getState();
            TrapDoor trapDoor = (TrapDoor) state.getData();
            trapDoor.setOpen(!trapDoor.isOpen());
            state.setData(trapDoor);
            state.update();
        }, 0L);
        e.setCancelled(true);
    }

}
