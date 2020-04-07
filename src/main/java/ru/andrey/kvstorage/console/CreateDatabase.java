package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import static ru.andrey.kvstorage.console.DatabaseCommandResult.SimpleDatabaseCommandResult;

public class CreateDatabase implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final Database database;

    public CreateDatabase(ExecutionEnvironment env, String databaseName, Database database) {
        this.env = env;
        this.databaseName = databaseName;
        this.database = database;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(x ->
                SimpleDatabaseCommandResult.error(
                        String.format("Database with name " + databaseName + " already exists")
                )
        ).orElseGet(() -> {
            env.addDatabase(database);
            return SimpleDatabaseCommandResult.success(
                    String.format("Database " + databaseName + " is created successfully")
            );
        });
    }
}