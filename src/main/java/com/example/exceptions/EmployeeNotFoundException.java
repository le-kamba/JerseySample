package com.example.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("そのIDのEmployeeは見つかりません。");
    }
}
