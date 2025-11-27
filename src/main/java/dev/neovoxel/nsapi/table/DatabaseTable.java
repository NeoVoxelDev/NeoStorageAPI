package dev.neovoxel.nsapi.table;

import com.zaxxer.hikari.HikariDataSource;
import dev.neovoxel.nsapi.action.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTable extends Table {
    private final HikariDataSource dataSource;

    public DatabaseTable(String name, HikariDataSource dataSource) {
        super(name);
        this.dataSource = dataSource;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public DatabaseCreator create() {
        return new DatabaseCreator(this);
    }

    @Override
    public DatabaseInserter insert() {
        return new DatabaseInserter(this);
    }

    @Override
    public DatabaseModifier alter() {
        return new DatabaseModifier(this);
    }

    @Override
    public DatabaseUpdater update() {
        return new DatabaseUpdater(this);
    }

    @Override
    public DatabaseDeleter delete() {
        return new DatabaseDeleter(this);
    }

    @Override
    public void drop() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + getName());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DatabaseSelector select(String... column) {
        return new DatabaseSelector(this, column);
    }
}
