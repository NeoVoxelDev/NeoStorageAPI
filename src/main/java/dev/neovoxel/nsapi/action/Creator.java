package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.Table;

import java.util.HashMap;
import java.util.Map;

public abstract class Creator {
    private final Table table;
    protected final Map<String, String> columnsAdd = new HashMap<>();

    protected Creator(Table table) {
        this.table = table;
    }

    public Creator column(String columnName, String columnType) {
        columnsAdd.put(columnName, columnType);
        return this;
    }

    public abstract void execute();

}
