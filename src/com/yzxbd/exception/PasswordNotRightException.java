package com.yzxbd.exception;

public class PasswordNotRightException extends RuntimeException {
    public PasswordNotRightException(String msg) {
        super(msg);
    }
}
