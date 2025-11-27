package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Modifier {
    private final Table table;
    protected final Map<String, String> columnsAdd = new HashMap<>();
    protected final List<String> columnsRemove = new ArrayList<>();

    protected Modifier(Table table) {
        this.table = table;
    }

    public Modifier add(String columnName, String columnType) {
        columnsAdd.put(columnName, columnType);
        return this;
    }

    public Modifier remove(String columnName) {
        columnsRemove.add(columnName);
        return this;
    }

    public abstract void execute();
}
