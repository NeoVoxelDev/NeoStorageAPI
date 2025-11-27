package dev.neovoxel.nsapi.entity;

import java.util.List;

public abstract class Result {
    public abstract List<Row> map();

    public abstract Row getFirst();

    public abstract Row get(int index);
}
