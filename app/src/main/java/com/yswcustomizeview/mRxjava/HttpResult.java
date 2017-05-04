package com.yswcustomizeview.mRxjava;

/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult<T> {

    private int returnResult;
    private String returnDetail;
    private T data;

    public int getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(int returnResult) {
        this.returnResult = returnResult;
    }

    public String getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(String returnDetail) {
        this.returnDetail = returnDetail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
