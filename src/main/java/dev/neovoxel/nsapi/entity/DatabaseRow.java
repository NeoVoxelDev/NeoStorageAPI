package dev.neovoxel.nsapi.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DatabaseRow extends Row {
    private final Map<String, Object> map = new HashMap<>();

    public DatabaseRow(Map<String, Object> map) {
        this.map.putAll(map);
    }

    @Override
    public String getString(String column) {
        return map.get(column).toString();
    }

    @Override
    public int getInt(String column) {
        return Integer.parseInt(map.get(column).toString());
    }

    @Override
    public long getLong(String column) {
        return Long.parseLong(map.get(column).toString());
    }

    @Override
    public <T> T getObject(String column, T type) {
        return (T) map.get(column);
    }

    @Override
    public Object getObject(String column) {
        return map.get(column);
    }

    @Override
    public boolean getBoolean(String column) {
        return Boolean.parseBoolean(map.get(column).toString());
    }

    @Override
    public BigDecimal getBigDecimal(String column) {
        return new BigDecimal(map.get(column).toString());
    }

    @Override
    public double getDouble(String column) {
        return Double.parseDouble(map.get(column).toString());
    }

    @Override
    public float getFloat(String column) {
        return Float.parseFloat(map.get(column).toString());
    }
}
