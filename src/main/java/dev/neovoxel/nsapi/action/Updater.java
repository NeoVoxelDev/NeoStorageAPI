package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.Table;
import dev.neovoxel.nsapi.util.Logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Updater {
    private final Table table;
    
    protected final Map<String, Object> updates = new LinkedHashMap<>();
    protected StringBuilder limit = new StringBuilder();
    protected final List<Object> limitsValue = new ArrayList<>();
    private boolean hasLogic = true;

    protected Updater(Table table) {
        this.table = table;
    }

    public Updater where(String column, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" = ?");
        limitsValue.add(value);
        hasLogic = false;
        return this;
    }

    public Updater where(String column, String operator, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" ").append(operator).append(" ?");
        limitsValue.add(value);
        hasLogic = false;
        return this;
    }

    public Updater logic(Logic logic) {
        limit.append(" ").append(logic.toString()).append(" ");
        hasLogic = true;
        return this;
    }

    public Updater set(String column, Object value) {
        updates.put(column, value);
        return this;
    }

    public abstract void execute();
}
