package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static dev.neovoxel.nsapi.util.TypeAdapter.setParameter;

public class DatabaseUpdater extends Updater {
    private final DatabaseTable table;

    public DatabaseUpdater(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    @Override
    public void execute() {
        StringBuilder sql = new StringBuilder("UPDATE ")
                .append(table.getName())
                .append(" SET ")
                .append(updates.keySet().stream().map(s -> s + " = ?").collect(Collectors.joining(", ")));
        if (!limit.toString().isEmpty()) {
            sql.append(" WHERE ").append(limit);
        }
        try (Connection connection = table.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            int i = 1;
            for (Object value : updates.values()) {
                setParameter(statement, i, value);
                i++;
            }
            for (Object limit : limitsValue) {
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
