package dev.neovoxel.nsapi;

import dev.neovoxel.nsapi.entity.Result;
import dev.neovoxel.nsapi.table.DatabaseTable;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void test() {
        DatabaseStorage storage = new DatabaseStorage("jdbc:mysql://localhost:3306/test", "root", "passw0rd");
        storage.load();
        DatabaseTable table = storage.table("nsapi_test");
        table.drop();
        table.create()
                .column("id", "int", "auto_increment primary key")
                .execute();
        table.alter()
                .add("name", "varchar(255)", "not null")
                .execute();
        table.insert()
                .column("name", "Alazeprt")
                .execute();
        table.insert()
                .column("name", "Test")
                .execute();
        table.update()
                .where("id", 2)
                .set("name", "Test114514")
                .execute();
        table.delete()
                .where("id", 1)
                .execute();
        Result result = table.select("id", "name").execute();
        result.map().forEach(row -> {
            System.out.println(row.getLong("id") + " " + row.getString("name"));
        });
    }
}
