package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

public class NewDatabase implements Database {
    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        throw new UnsupportedOperationException();
    }
}