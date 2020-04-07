package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static class SimpleDatabaseCommandResult implements DatabaseCommandResult {

        private final Optional<String> result;
        private String errorMessage;
        private final DatabaseCommandStatus status;

        public SimpleDatabaseCommandResult(String result, DatabaseCommandStatus status) {
            this.result = Optional.ofNullable(result);
            this.status = status;
        }

        public SimpleDatabaseCommandResult(String result, DatabaseCommandStatus status, String errorMessage) {
            this(result, status);
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            return result;
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        public static SimpleDatabaseCommandResult success(String result) {
            return new SimpleDatabaseCommandResult(result, DatabaseCommandStatus.SUCCESS);
        }

        public static SimpleDatabaseCommandResult error(String message) {
            return new SimpleDatabaseCommandResult(null, DatabaseCommandStatus.FAILED, message);
        }
    }
}