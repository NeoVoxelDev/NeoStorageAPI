package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.entity.DatabaseResult;
import dev.neovoxel.nsapi.entity.DatabaseRow;
import dev.neovoxel.nsapi.entity.Result;
import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.neovoxel.nsapi.util.TypeAdapter.setParameter;

public class DatabaseSelector extends Selector {
    private final DatabaseTable table;

    public DatabaseSelector(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    public DatabaseSelector(DatabaseTable table, String... columns) {
        super(table, columns);
        this.table = table;
    }

    @Override
    public Result execute() {
        StringBuilder sql = new StringBuilder("SELECT ")
                .append(String.join(", ", columns))
                .append(" FROM ")
                .append(table.getName());
        if (!limit.toString().isEmpty()) {
            sql.append(" WHERE ").append(limit);
        }
        try (Connection connection = this.table.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            int i = 1;
            for (Object param : limitsValue) {
                setParameter(statement, i, param);
                i++;
            }
            ResultSet resultSet = statement.executeQuery();

            List<DatabaseRow> rows = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (String column : columns) {
                    map.put(column, resultSet.getObject(column));
                }
                rows.add(new DatabaseRow(map));
            }

            resultSet.close();
            statement.close();

            return new DatabaseResult(rows, columns);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
