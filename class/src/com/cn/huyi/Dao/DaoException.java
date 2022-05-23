package com.cn.huyi.Dao;

public class DaoException extends Exception {
    private static final long serialVersionUIP =19192L;
    private String message;

    public DaoException(){ };

    public DaoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DaoException{" +
                "message='" + message + '\'' +
                '}';
    }
}
