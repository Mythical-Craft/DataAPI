package net.mythical.datamanager;

import net.mythical.datamanager.sql.DataManager;
import net.mythical.datamanager.sql.provider.MySQL;
import net.mythical.datamanager.sql.provider.PostgreSQL;
import org.bukkit.plugin.java.JavaPlugin;

public class DataAPI extends JavaPlugin {

    public DataManager postgre = new PostgreSQL();
    public MySQL mysql = new MySQL();

    public static DataAPI plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static MySQL MySQL() {
        return plugin.mysql;
    }

    public static DataManager PostgreSQL() {
        return plugin.postgre;
    }
}
