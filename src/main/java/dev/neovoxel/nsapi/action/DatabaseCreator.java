package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.DatabaseTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseCreator extends Creator {
    private final DatabaseTable table;
    private final Map<String, String> extras = new HashMap<>();

    public DatabaseCreator(DatabaseTable table) {
        super(table);
        this.table = table;
    }

    public Creator column(String columnName, String columnType, String extraOptions) {
        extras.put(columnName, extraOptions);
        return super.column(columnName, columnType);
    }

    @Override
    public void execute() {
        try (Connection connection = table.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + table.getName() + " (";
            int i = 0;
            for (Map.Entry<String, String> pair : columnsAdd.entrySet()) {
                sql += pair.getKey() + " " + pair.getValue();
                if (extras.containsKey(pair.getKey())) {
                    sql += " " + extras.get(pair.getKey());
                }
                if (i != columnsAdd.size() - 1) {
                    sql += ", ";
                }
                i++;
            }
            sql += ")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
