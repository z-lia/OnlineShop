package com.example.onlineshop.network;

public class ResponseError {

//    @SerializedName("mCode")
//    private String mCode;
//
//    @SerializedName("mMessage")
//    private String mMessage;
    private int mCode;
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}
