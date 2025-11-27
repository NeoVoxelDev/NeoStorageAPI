package dev.neovoxel.nsapi.entity;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseResult extends Result {
    private final List<DatabaseRow> data = new ArrayList<>();
    private final List<String> columns = new ArrayList<>();

    public DatabaseResult(List<DatabaseRow> data, List<String> columns) {
        this.data.addAll(data);
        this.columns.addAll(columns);
    }

    @Override
    public List<Row> map() {
        return data.stream().map(s -> (Row) s).collect(Collectors.toList());
    }

    @Override
    public DatabaseRow getFirst() {
        return data.get(0);
    }

    @Override
    public DatabaseRow get(int index) {
        return data.get(index);
    }
}
