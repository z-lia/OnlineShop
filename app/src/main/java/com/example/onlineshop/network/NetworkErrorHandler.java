package com.example.onlineshop.network;

public class NetworkErrorHandler {
    public static String getNetworkMessage (int errorCode){
        String message ="";
        switch (errorCode){
            case 400:
                message ="This email is used before. Please register with another one!";
                break;
            case 500:
                message = "Server is not available";
                break;
        }

        return  message;
    }
}
