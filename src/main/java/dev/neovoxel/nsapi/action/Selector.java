package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.entity.Result;
import dev.neovoxel.nsapi.table.Table;
import dev.neovoxel.nsapi.util.Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Selector {
    private final Table table;
    protected final StringBuilder limit = new StringBuilder();
    protected final List<Object> limitsValue = new ArrayList<>();
    protected final List<String> columns = new ArrayList<>();
    private boolean hasLogic = true;

    protected Selector(Table table) {
        this.table = table;
    }

    protected Selector(Table table, String... columns) {
        this.table = table;
        this.columns.addAll(Arrays.asList(columns));
    }

    public Selector where(String column, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" = ?");
        limitsValue.add(value);
        hasLogic = false;
        return this;
    }

    public Selector where(String column, String operator, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" ").append(operator).append(" ?");
        limitsValue.add(value);
        hasLogic = false;
        return this;
    }

    public Selector logic(Logic logic) {
        limit.append(" ").append(logic.toString()).append(" ");
        hasLogic = true;
        return this;
    }

    public Selector column(String column) {
        columns.add(column);
        return this;
    }

    public Selector column(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public Selector all() {
        columns.clear();
        columns.add("*");
        return this;
    }

    public abstract Result execute();
}
