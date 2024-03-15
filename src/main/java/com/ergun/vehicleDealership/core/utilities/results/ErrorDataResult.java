package com.ergun.vehicleDealership.core.utilities.results;

public class ErrorDataResult<T> extends DataResult{
    public ErrorDataResult(boolean s1, T data) {
        super(false, data);
    }

    public ErrorDataResult(String message, T data) {
        super(message, false, data);
    }

    public ErrorDataResult(String message) {
        super(message, false, null);
    }

    public ErrorDataResult() {
        super(false, null);
    }
}
