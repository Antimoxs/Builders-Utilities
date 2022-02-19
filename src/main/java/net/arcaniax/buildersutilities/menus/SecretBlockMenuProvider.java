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
package net.arcaniax.buildersutilities.menus;

import net.arcaniax.buildersutilities.menus.inv.ClickableItem;
import net.arcaniax.buildersutilities.menus.inv.content.InventoryContents;
import net.arcaniax.buildersutilities.menus.inv.content.InventoryProvider;
import net.arcaniax.buildersutilities.utils.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SecretBlockMenuProvider implements InventoryProvider {

    private static final ItemStack SPAWNER = Items.create(Material.MOB_SPAWNER, ChatColor.BLUE + "Spawner", "");
    private static final ItemStack BARRIER = Items.create(Material.BARRIER, ChatColor.RED + "Barrier", "");
    private static final ItemStack DRAGON_EGG = Items.create(
            Material.DRAGON_EGG,
            ChatColor.LIGHT_PURPLE + "Dragon Egg",
            ""
    );
    private static final ItemStack LIGHT = Items.create(
            "LIGHT",
            ChatColor.GOLD + "Light Block",
            ""
    );

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(0, 1, ClickableItem.of(SPAWNER, inventoryClickEvent -> player.getInventory().addItem(SPAWNER)));
        contents.set(0, 2, ClickableItem.of(BARRIER, inventoryClickEvent -> player.getInventory().addItem(BARRIER)));
        contents.set(0, 4, ClickableItem.of(DRAGON_EGG, inventoryClickEvent -> player.getInventory().addItem(DRAGON_EGG)));
        if (LIGHT != null) {
            contents.set(0, 6, ClickableItem.of(LIGHT, inventoryClickEvent -> player.getInventory().addItem(LIGHT)));
        }
    }


}
