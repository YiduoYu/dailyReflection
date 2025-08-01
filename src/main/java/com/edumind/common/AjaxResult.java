package com.edumind.common;

import lombok.Data;

@Data
public class AjaxResult {
    private boolean success;
    private String message;
    private Object data;

    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        result.setMessage("Operation successful");
        result.setData(data);
        return result;
    }

    public static AjaxResult error(String msg) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }

    public static AjaxResult success() {
        return success(null);
    }
}