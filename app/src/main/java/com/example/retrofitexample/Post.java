package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

class Post {

    private String status;

    private String message;

    private String notif_id;

    String getStatus() {
        return status;
    }

    String getMessage() {
        return message;
    }

    public String getNotif_id() {
        return notif_id;
    }
}
