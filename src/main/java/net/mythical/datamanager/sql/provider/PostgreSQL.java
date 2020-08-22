package net.mythical.datamanager.sql.provider;

import net.mythical.datamanager.DataAPI;
import net.mythical.datamanager.sql.DataManager;
import net.mythical.lib.com.zaxxer.hikari.HikariDataSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PostgreSQL extends DataManager {

    private HikariDataSource hikari;

    private String poolName = "MythPostgre";
    private int maxPool = 10;
    private int maxLifetime = 120000;
    private String JdbcUrl = "jdbc:postgresql://127.0.0.1:5432/public";
    private String username = "postgres";
    private String password = "password";
    private boolean useDefaultProperty = false;
    private final Map<String, String> dataProperty = new HashMap<>();

    @Override
    public void init() {
        hikari = new HikariDataSource();
        hikari.setPoolName(this.poolName);
        hikari.setMaximumPoolSize(this.maxPool);
        hikari.setMaxLifetime(this.maxLifetime);
        hikari.setDriverClassName("net.mythical.lib.org.postgresql.Driver");
        hikari.setJdbcUrl(this.JdbcUrl);
        hikari.setUsername(this.username);
        hikari.setPassword(this.password);
        if (this.useDefaultProperty){
            hikari.addDataSourceProperty("reWriteBatchedInserts", "true");
            hikari.addDataSourceProperty("cachePrepStmts", "true");
            hikari.addDataSourceProperty("preparedStatementCacheQueries", "275");
        }
        for (String propertyName : dataProperty.keySet()){
            hikari.addDataSourceProperty(propertyName, dataProperty.get(propertyName));
        }
    }

    @Override
    public void createTable(String tableName, List<String> column){
        createTableSQL(tableName, column, hikari);
    }

    public void executeUpdate(final String string) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = hikari.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(string)){
                    preparedStatement.executeUpdate();
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
        }.runTaskAsynchronously(DataAPI.plugin);
    }

    @Override
    public void query(String sqlQuery, Consumer<ResultSet> consumer){
        new BukkitRunnable() {
            @Override
            public void run() {
                try(Connection connection = hikari.getConnection();
                    Statement statement = connection.createStatement()){
                    consumer.accept(statement.executeQuery(sqlQuery));
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(DataAPI.plugin);
    }

    public void setHikari(HikariDataSource hikari) {
        this.hikari = hikari;
    }

    @Override
    public void setJdbcUrl(String jdbcUrl) {
        JdbcUrl = jdbcUrl;
    }

    @Override
    public void setMaxLifetime(int maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    @Override
    public void setMaxPool(int maxPool) {
        this.maxPool = maxPool;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public void setUseDefaultProperty(boolean useDefaultProperty) {
        this.useDefaultProperty = useDefaultProperty;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isUseDefaultProperty() {
        return useDefaultProperty;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public HikariDataSource getHikari() {
        return hikari;
    }

    @Override
    public int getMaxLifetime() {
        return maxLifetime;
    }

    @Override
    public int getMaxPool() {
        return maxPool;
    }

    @Override
    public Map<String, String> getDataProperty() {
        return dataProperty;
    }

    @Override
    public String getJdbcUrl() {
        return JdbcUrl;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getPoolName() {
        return poolName;
    }
}
