package com.ergun.vehicleDealership.core.utilities.results;

import lombok.Getter;

@Getter
public class Result {
    private String message;
    private boolean success;

    public Result(boolean s1){
        success = s1;
    }
    public Result(String m1, boolean s1){
        message = m1;
        success = s1;
    }
}
