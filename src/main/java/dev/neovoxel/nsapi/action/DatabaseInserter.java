package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.neovoxel.nsapi.util.TypeAdapter.setParameter;

public class DatabaseInserter extends Inserter {
    private final DatabaseTable table;

    public DatabaseInserter(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    @Override
    public void execute() {
        StringBuilder sql = new StringBuilder("INSERT INTO ")
                .append(table.getName())
                .append(" (")
                .append(String.join(", ", map.keySet()))
                .append(") VALUES (");
        for (int i = 0; i < map.size(); i++) {
            if (i != map.size() - 1) {
                sql.append("?, ");
            } else {
                sql.append("?)");
            }
        }
        try (Connection connection = table.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            int i = 1;
            for (Object param : map.values()) {
                setParameter(statement, i, param);
                i++;
            }
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
