package com.my.lottery.base;

import com.github.pagehelper.Page;

/**
 * controller父类
 *
 * @param <T>
 */
public abstract class BaseController<T> {

    protected <T> ResponseObject success(T data) {
        return ResponseObject.successResponse(data);
    }

    protected ResponseObject success() {
        return ResponseObject.successResponse(null);
    }

    protected <T> ResponseObject successPage(Page<T> page) {
        return ResponseObject.successPageResponse(page);
    }

}
