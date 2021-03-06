package net.mythical.datamanager.sql.provider;

import net.mythical.datamanager.sql.DataManager;
import net.mythical.lib.com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MySQL extends DataManager {

    public HikariDataSource hikari;

    private String poolName = "MythMySQL";
    private int maxPool = 10;
    private int maxLifetime = 120000;
    private String JdbcUrl = "jdbc:mysql://127.0.0.1:3306/public";
    private String username = "root";
    private String password = "password";
    private boolean useDefaultProperty = false;
    private final Map<String, String> dataProperty = new HashMap<>();

    @Override
    public void init() {
        hikari = new HikariDataSource();
        hikari.setPoolName(this.poolName);
        hikari.setMaximumPoolSize(this.maxPool);
        hikari.setMaxLifetime(this.maxLifetime);
        hikari.setJdbcUrl(this.JdbcUrl);
        hikari.setUsername(this.username);
        hikari.setPassword(this.password);
        if (this.useDefaultProperty){
            hikari.addDataSourceProperty("cachePrepStmts", "true");
            hikari.addDataSourceProperty("prepStmtCacheSize", "275");
            hikari.addDataSourceProperty("prepStmtCacheSqlLimit", "4096");
        }
        for (String propertyName : dataProperty.keySet()) {
            hikari.addDataSourceProperty(propertyName, dataProperty.get(propertyName));
        }
    }

    @Override
    public void createTable(String tableName, List<String> column){
        createTableSQL(tableName, column, hikari);
    }


    public void execute(final String string) {
        executeSQL(string, hikari);
    }

    @Override
    public void query(String sqlQuery, Consumer<ResultSet> consumer){
        CompletableFuture.runAsync(() -> {
            try(Connection connection = hikari.getConnection();
                Statement statement = connection.createStatement()){
                consumer.accept(statement.executeQuery(sqlQuery));
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
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
    public void close() {
        hikari.close();
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
