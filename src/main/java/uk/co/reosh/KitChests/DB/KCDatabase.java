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

package uk.co.reosh.KitChests.DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import uk.co.reosh.KitChests.KCChest;
import uk.co.reosh.KitChests.KitChests;

public class KCDatabase {

	KitChests plugin;
	Connection c;

	public KCDatabase(KitChests p, String dbfile) {
		plugin = p;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
		} catch (Exception e) {
			plugin.logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	public void verifyDatabase() {
		try {
			DatabaseMetaData dbm = c.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "CHESTS", null);
			if (!tables.next()) {
				createDatabase();
			}
		} catch (Exception e) {
			plugin.logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			createDatabase();
		}
	}
	private void createDatabase() {
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			
			// Create table "CHESTS"
			String sql = "CREATE TABLE CHESTS "
					   + "(ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT,"
					   + " X              INT     NOT NULL,"
					   + " Y              INT     NOT NULL,"
				       + " Z              INT     NOT NULL,"
					   + " KIT			   TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			
			// Create table "KITS"
			sql = "CREATE TABLE KITS "
				+ "(ID INT PRIMARY KEY     NOT NULL,"
				+ " NAME           TEXT    NOT NULL,"
				+ " ITEMS          TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	public boolean addChest(KCChest chest) {
		try {
			Statement stmt;
			stmt = c.createStatement();
			String sql = "INSERT INTO CHESTS (X, Y, Z, KIT) " +
						 "VALUES (" + chest.getLocation().getBlockX() + ", " + chest.getLocation().getBlockY() + ", " + chest.getLocation().getBlockZ() + ", '" + chest.getKit() + "'" + ");"; 
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
