package dev.neovoxel.nsapi.table;

import dev.neovoxel.nsapi.action.*;

public abstract class Table {
    private final String name;

    protected Table(String name) {
        this.name = name;
    }

    public abstract Creator create();

    public abstract Inserter insert();

    public abstract Modifier alter();

    public abstract Deleter delete();

    public abstract void drop();

    public abstract Updater update();

    public abstract Selector select(String... column);

    public String getName() {
        return name;
    }
}
