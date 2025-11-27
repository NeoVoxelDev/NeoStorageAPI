package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseModifier extends Modifier {
    private final DatabaseTable table;
    private final Map<String, String> extras = new HashMap<>();

    public DatabaseModifier(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    public DatabaseModifier add(String columnName, String columnType, String extraOptions) {
        add(columnName, columnType);
        extras.put(columnName, extraOptions);
        return this;
    }

    @Override
    public void execute() {
        try (Connection connection = table.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            for (String columnName : columnsAdd.keySet()) {
                String sql = "ALTER TABLE " + table.getName() + " ADD " + columnName + " " + columnsAdd.get(columnName);
                if (extras.containsKey(columnName)) {
                    sql += " " + extras.get(columnName);
                }
                statement.execute(sql);
            }
            for (String columnName : columnsRemove) {
                statement.execute("ALTER TABLE " + table.getName() + " DROP COLUMN " + columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
