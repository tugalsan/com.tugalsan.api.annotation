package com.tugalsan.api.annotation.server;

import jakarta.validation.Payload;

public class Severity {

    public static class Info implements Payload {
    }

    public static class Error implements Payload {
    }
}
