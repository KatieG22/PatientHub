package com.patienthub.errors;

/**
 * class handles simple error display
 * message represents message to display to user
 * detail gives a more developer friendly relation
 * such as field name e.g
 * message : amount cannot be below 200
 * detail: amount
 * where amount represents field name
 * this can help the client connect what fields
 * are affected
 */
public class ConstraintError {
    private String message;
    private String detail;

    public ConstraintError() {

    }

    public ConstraintError(String message, String detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
