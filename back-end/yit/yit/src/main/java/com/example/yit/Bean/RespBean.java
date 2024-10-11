package com.example.yit.Bean;

public record RespBean(String status, String msg, Object data) {
    public RespBean(){this(null, null, null);}
}
