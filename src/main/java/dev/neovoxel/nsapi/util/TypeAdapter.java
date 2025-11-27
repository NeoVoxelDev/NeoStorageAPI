package dev.neovoxel.nsapi.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TypeAdapter {
    public static void setParameter(PreparedStatement statement, int index, Object value) throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.NULL);
        } else if (value instanceof String) {
            statement.setString(index, (String) value);
        } else if (value instanceof Integer) {
            statement.setInt(index, (Integer) value);
        } else if (value instanceof Long) {
            statement.setLong(index, (Long) value);
        } else if (value instanceof Double) {
            statement.setDouble(index, (Double) value);
        } else if (value instanceof Float) {
            statement.setFloat(index, (Float) value);
        } else if (value instanceof Boolean) {
            statement.setBoolean(index, (Boolean) value);
        } else if (value instanceof Date) {
            statement.setDate(index, (Date) value);
        } else if (value instanceof java.sql.Timestamp) {
            statement.setTimestamp(index, (java.sql.Timestamp) value);
        } else if (value instanceof java.sql.Time) {
            statement.setTime(index, (java.sql.Time) value);
        } else if (value instanceof byte[]) {
            statement.setBytes(index, (byte[]) value);
        } else {
            statement.setObject(index, value);
        }
    }
}
