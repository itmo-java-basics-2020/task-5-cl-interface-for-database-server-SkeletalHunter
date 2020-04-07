package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

import static ru.andrey.kvstorage.console.DatabaseCommandResult.SimpleDatabaseCommandResult;

public class CreateTable implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;

    public CreateTable(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
            try {
                database.createTableIfNotExists(tableName);
                return SimpleDatabaseCommandResult.success(
                        String.format("Table " + tableName + " in database " + databaseName + " is created successfully")
                );
            } catch (DatabaseException ex) {
                return SimpleDatabaseCommandResult.error(ex.getMessage());
            }
        }).orElseGet(() -> SimpleDatabaseCommandResult.error(
                String.format("Database " + databaseName + " doesn't exist")
        ));
    }
}