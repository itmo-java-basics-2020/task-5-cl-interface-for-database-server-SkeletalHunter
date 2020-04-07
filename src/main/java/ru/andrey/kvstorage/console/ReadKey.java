package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.console.DatabaseCommandResult.SimpleDatabaseCommandResult;

public class ReadKey implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public ReadKey(ExecutionEnvironment env,
                   String databaseName,
                   String tableName,
                   String key) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
                    try {
                        return SimpleDatabaseCommandResult.success(database.read(tableName, key));
                    } catch (DatabaseException e) {
                        return SimpleDatabaseCommandResult.error(e.getMessage());
                    }
                }
        ).orElseGet(() -> SimpleDatabaseCommandResult.error(String.format("Database with name " + databaseName + " doesn't exist"))
        );
    }
}