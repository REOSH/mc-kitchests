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

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import uk.co.reosh.KitChests.DB.KCDatabase;

public class KitChests extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static KCDatabase database = null;
	
	public void onEnable() {
	    this.saveDefaultConfig();
	    
		database = new KCDatabase(this, this.getDataFolder().getAbsolutePath() + File.separator + "chests.db");
		database.verifyDatabase();
	}
}
		
	
