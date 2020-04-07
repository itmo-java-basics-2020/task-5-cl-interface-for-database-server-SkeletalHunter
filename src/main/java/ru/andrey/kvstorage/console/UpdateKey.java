package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.DatabaseCommandResult.SimpleDatabaseCommandResult;
import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKey implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public UpdateKey(ExecutionEnvironment env,
                     String databaseName,
                     String tableName,
                     String key,
                     String value) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
            try {
                database.write(tableName, key, value);
                return SimpleDatabaseCommandResult.success(
                        String.format("Key " + key + " has value " + value + " in table " + tableName + " in database " + databaseName)
                );
            } catch (DatabaseException ex) {
                return SimpleDatabaseCommandResult.error(ex.getMessage());
            }
        }).orElseGet(() ->
                SimpleDatabaseCommandResult.error(
                        String.format("Database with name " + databaseName + " doesn't exist")
                )
        );
    }
}