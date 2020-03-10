package com.example.exceptions;

public class EmployeeNameNotFoundException extends RuntimeException {
    public EmployeeNameNotFoundException(String name) {
        super("文字列{" + name + "}を含む名前のEmployeeは存在しません。");
    }
}
