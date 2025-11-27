package dev.neovoxel.nsapi.entity;

import java.math.BigDecimal;

public abstract class Row {
    public abstract String getString(String column);
    public abstract int getInt(String column);
    public abstract long getLong(String column);
    public abstract <T> T getObject(String column, T type);
    public abstract Object getObject(String column);
    public abstract boolean getBoolean(String column);
    public abstract BigDecimal getBigDecimal(String column);
    public abstract double getDouble(String column);
    public abstract float getFloat(String column);
}
