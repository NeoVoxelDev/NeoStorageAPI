package dev.neovoxel.nsapi.action;

import dev.neovoxel.nsapi.table.Table;
import dev.neovoxel.nsapi.util.Logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Deleter {
    private final Table table;

    protected final StringBuilder limit = new StringBuilder();
    protected final List<Object> limitValues = new ArrayList<>();
    private boolean hasLogic = true;

    protected Deleter(Table table) {
        this.table = table;
    }

    public Deleter where(String column, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" = ?");
        limitValues.add(value);
        hasLogic = false;
        return this;
    }

    public Deleter where(String column, String operator, Object value) {
        if (!hasLogic) {
            limit.append(" AND ");
        }
        limit.append(column).append(" ").append(operator).append(" ?");
        limitValues.add(value);
        hasLogic = false;
        return this;
    }

    public Deleter logic(Logic logic) {
        limit.append(" ").append(logic.toString()).append(" ");
        hasLogic = true;
        return this;
    }

    public abstract void execute();
}
