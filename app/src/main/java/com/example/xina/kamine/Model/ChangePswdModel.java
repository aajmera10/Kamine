package com.example.xina.kamine.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePswdModel {
    @SerializedName("detail")
    @Expose
    private ChangePswdDetails detail;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public ChangePswdDetails getDetail() {
        return detail;
    }

    public void setDetail(ChangePswdDetails detail) {
        this.detail = detail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
