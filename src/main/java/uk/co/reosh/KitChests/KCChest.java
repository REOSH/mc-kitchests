/*
 * KitChests Bukkit Plugin
 * Copyright (C) 2014 REOSH Group <http://reosh.co.uk/> and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package uk.co.reosh.KitChests;

import org.bukkit.Location;
import org.bukkit.Material;

public class KCChest {

	Location location;
	String kit;
	
	public KCChest(String kit, Location loc) {
		this.kit = kit;
		this.location = loc;
	}
	
	public Location getLocation() {
		return this.location;
	}
	public String getKit() {
		return this.kit;
	}
	
	public void respawn() {
		location.getBlock().setType(Material.CHEST);
		restock();
	}
	
	public void restock() {
		// TODO: Add restocking.
	}
}