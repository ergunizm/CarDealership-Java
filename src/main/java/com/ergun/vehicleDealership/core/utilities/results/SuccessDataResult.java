package com.ergun.vehicleDealership.core.utilities.results;

public class SuccessDataResult<T> extends DataResult{
    public SuccessDataResult(boolean s1, T data) {
        super(true, data);
    }

    public SuccessDataResult(String message, T data) {
        super(message, true, data);
    }

    public SuccessDataResult(String message) {
        super(message, true, null);
    }

    public SuccessDataResult() {
        super(true, null);
    }
}
