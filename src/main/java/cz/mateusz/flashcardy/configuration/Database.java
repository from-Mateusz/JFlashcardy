package cz.mateusz.flashcardy.configuration;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class Database {

    private final MongoDatabaseFactory databaseFactory;

    public Database(MongoDatabaseFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }
}
