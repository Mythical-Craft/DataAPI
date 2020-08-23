package net.mythical.datamanager.sql;


import net.mythical.datamanager.DataAPI;
import net.mythical.lib.com.zaxxer.hikari.HikariDataSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class DataManager {

    protected static void createTableSQL(String tableName, List<String> column, HikariDataSource hikari) {
        new BukkitRunnable() {

            @Override
            public void run() {
                try (Connection connection = hikari.getConnection();
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS" + tableName + " (" + String.join(",", column) + ")");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(DataAPI.plugin);
    }

    protected static void executeSQL(final String string, HikariDataSource hikari){
        new BukkitRunnable() {

            @Override
            public void run() {
                try (Connection connection = hikari.getConnection();
                     Statement statement = connection.createStatement()){
                    statement.execute(string);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(DataAPI.plugin);
    }

    public abstract void init();

    public abstract void setJdbcUrl(String jdbcUrl);

    public abstract void setMaxLifetime(int maxLifetime);

    public abstract void setMaxPool(int maxPool);

    public abstract void setPassword(String password);

    public abstract void setPoolName(String poolName);

    public abstract void setHikari(HikariDataSource hikari);

    public abstract void setUseDefaultProperty(boolean useDefaultProperty);

    public abstract void setUsername(String username);

    public abstract void close();

    public abstract void execute(final String string);

    public abstract void query(String sqlQuery, Consumer<ResultSet> consumer);

    public abstract void createTable(String tableName, List<String> column);

    public abstract boolean isUseDefaultProperty();

    public abstract String getUsername();

    public abstract HikariDataSource getHikari();

    public abstract int getMaxLifetime();

    public abstract int getMaxPool();

    public abstract Map<String, String> getDataProperty();

    public abstract String getJdbcUrl();

    public abstract String getPassword();

    public abstract String getPoolName();
}
