package net.mythical.datamanager.sql;


import net.mythical.lib.com.zaxxer.hikari.HikariDataSource;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class DataManager {

    public abstract void init();

    public abstract void setJdbcUrl(String jdbcUrl);

    public abstract void setMaxLifetime(int maxLifetime);

    public abstract void setMaxPool(int maxPool);

    public abstract void setPassword(String password);

    public abstract void setPoolName(String poolName);

    public abstract void setHikari(HikariDataSource hikari);

    public abstract void setUseDefaultProperty(boolean useDefaultProperty);

    public abstract void setUsername(String username);

    public abstract void query(String sqlQuery, Consumer<ResultSet> consumer);

    public abstract void createDatabase(String tableName, List<String> column);

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
