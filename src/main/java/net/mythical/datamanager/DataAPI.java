package net.mythical.datamanager;

import net.mythical.datamanager.sql.DataManager;
import net.mythical.datamanager.sql.provider.MySQL;
import net.mythical.datamanager.sql.provider.PostgreSQL;
import org.bukkit.plugin.java.JavaPlugin;

public class DataAPI extends JavaPlugin {

    public static DataManager postgreSQL = new PostgreSQL();
    public static MySQL MySQL = new MySQL();
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
}
