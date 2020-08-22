package net.mythical.datamanager.sql.provider;

import net.mythical.datamanager.sql.DataManager;
import net.mythical.lib.com.zaxxer.hikari.HikariDataSource;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MySQL extends DataManager {

    @Override
    public void init() {

    }

    @Override
    public void setJdbcUrl(String jdbcUrl) {

    }

    @Override
    public void setMaxLifetime(int maxLifetime) {

    }

    @Override
    public void setMaxPool(int maxPool) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setPoolName(String poolName) {

    }

    @Override
    public void setHikari(HikariDataSource hikari) {

    }

    @Override
    public void setUseDefaultProperty(boolean useDefaultProperty) {

    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void query(String sqlQuery, Consumer<ResultSet> consumer) {

    }

    @Override
    public void createDatabase(String tableName, List<String> column) {

    }

    @Override
    public boolean isUseDefaultProperty() {
        return false;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public HikariDataSource getHikari() {
        return null;
    }

    @Override
    public int getMaxLifetime() {
        return 0;
    }

    @Override
    public int getMaxPool() {
        return 0;
    }

    @Override
    public Map<String, String> getDataProperty() {
        return null;
    }

    @Override
    public String getJdbcUrl() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getPoolName() {
        return null;
    }
}
