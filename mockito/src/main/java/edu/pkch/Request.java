package edu.pkch;

import lombok.Getter;

@Getter
public class Request {
    private Status status;
    private String name;

    private Request(Status status, String name) {
        this.status = status;
        this.name = name;
    }

    public static Request ofActive(String name) {
        return new Request(Status.ACTIVE, name);
    }

    public static Request ofWithdraw(String name) {
        return new Request(Status.WITHDRAW, name);
    }

    public enum Status {
        ACTIVE, WITHDRAW
    }
}
