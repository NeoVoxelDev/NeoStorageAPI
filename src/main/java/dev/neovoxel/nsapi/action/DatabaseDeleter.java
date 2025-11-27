package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.neovoxel.nsapi.util.TypeAdapter.setParameter;

public class DatabaseDeleter extends Deleter {
    private final DatabaseTable table;

    public DatabaseDeleter(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    @Override
    public void execute() {
        StringBuilder sql = new StringBuilder("DELETE FROM ")
                .append(table.getName());
        if (!limit.toString().isEmpty()) {
            sql.append(" WHERE ").append(limit);
        }
        try (Connection connection = this.table.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            int i = 1;
            for (Object limit : limitValues) {
                setParameter(statement, i, limit);
                i++;
            }
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
