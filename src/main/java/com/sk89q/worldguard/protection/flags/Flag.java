// $Id$
/*
 * WorldGuard
 * Copyright (C) 2010 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.sk89q.worldguard.protection.flags;

import org.bukkit.command.CommandSender;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 *
 * @author sk89q
 * @param <T>
 */
public abstract class Flag<T> {

    private String name;
    private RegionGroupFlag regionGroup;
    
    public Flag(String name, RegionGroup defaultGroup) {
        this.name = name;

        if (defaultGroup != null) {
            this.regionGroup = new RegionGroupFlag(name + "-group", defaultGroup);
        }
    }

    public Flag(String name) {
        this(name, RegionGroup.NON_MEMBERS);
    }

    public String getName() {
        return name;
    }
    
    public RegionGroupFlag getRegionGroupFlag() {
        return regionGroup;
    }

    public abstract T parseInput(WorldGuardPlugin plugin, CommandSender sender,
            String input) throws InvalidFlagFormat;

    public abstract T unmarshal(Object o);

    public abstract Object marshal(T o);

    @Override
    public final boolean equals(Object o) {
        if (o instanceof Flag<?>) {
            return ((Flag<?>)o).name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }
}
