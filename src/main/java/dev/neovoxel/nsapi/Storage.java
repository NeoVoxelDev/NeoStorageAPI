package dev.neovoxel.nsapi;

import dev.neovoxel.nsapi.table.Table;

public abstract class Storage {
    public abstract void load();

    public abstract void save();

    public abstract Table table(String name);
}
