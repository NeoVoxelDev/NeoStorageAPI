package dev.neovoxel.nsapi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.neovoxel.nsapi.table.DatabaseTable;
import dev.neovoxel.nsapi.table.Table;
import dev.neovoxel.nsapi.util.DatabaseStorageType;

import java.util.Properties;

public class DatabaseStorage extends Storage {
    private HikariDataSource dataSource;

    public DatabaseStorage(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        this.dataSource = dataSource;
    }

    public DatabaseStorage(String url, String username, String password, Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDataSourceProperties(properties);
        this.dataSource = new HikariDataSource(config);
    }

    public DatabaseStorage(HikariConfig hikariConfig) {
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    @Override
    public void load() {}

    @Override
    public void save() {
        dataSource.close();
    }

    @Override
    public DatabaseTable table(String name) {
        return new DatabaseTable(name, dataSource);
    }
}
