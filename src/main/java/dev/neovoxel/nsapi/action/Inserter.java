package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.Table;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Inserter {
    private final Table table;

    protected final Map<String, Object> map = new LinkedHashMap<>();

    protected Inserter(Table table) {
        this.table = table;
    }

    public Inserter column(String column, Object value) {
        map.put(column, value);
        return this;
    }

    public Inserter column(Map<String, Object> map) {
        this.map.putAll(map);
        return this;
    }

    public abstract void execute();
}
