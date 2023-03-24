package com.my.lottery.base;


import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求的返回结果。 如果:success=true data=处理结果； 如果:success=false code=错误代码 message=错误信息
 */
@Data
public class ResponseObject<T> implements Serializable {
    public static final int STATUS_SUCCESS = 0;
    private static final long serialVersionUID = 1L;
    private boolean success;

    private int code;

    private String message;

    private T data;

    private ResponseObject() {
    }

    /**
     * 构造一个成功响应对象
     *
     * @param data
     * @return
     */
    public static <T> ResponseObject successResponse(T data) {
        ResponseObject res = new ResponseObject();
        res.success = true;
        res.data = data;
        res.code = ResponseObject.STATUS_SUCCESS;
        return res;
    }

    /**
     * 返回分页相应对象
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> ResponseObject successPageResponse(Page<T> page) {
        ResponseObject res = new ResponseObject();
        res.success = true;
        PageObject pageObject = new PageObject();
        if (page != null) {
            pageObject.setDatas(page.getResult());
            pageObject.setTotal(page.getTotal());
            pageObject.setPageSize(page.getPageSize());
            pageObject.setPageIndex(page.getPageNum());
            pageObject.setPageCount(page.getPages());
        }
        res.data = pageObject;
        return res;
    }

    /**
     * 构造一个错误响应对象
     *
     * @param errorCode
     * @return
     */
    public static ResponseObject failResponse(int errorCode) {
        ResponseObject res = new ResponseObject();
        res.success = false;
        res.code = errorCode;
        return res;
    }

    /**
     * 构造一个错误响应对象
     *
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static ResponseObject failResponse(int errorCode, String errorMessage) {
        ResponseObject res = new ResponseObject();
        res.success = false;
        res.code = errorCode;
        res.message = errorMessage;
        return res;
    }


    /**
     * 用于构建分页相应对象
     *
     * @param <T>
     */
    @Data
    public static class PageObject<T> {
        private long total;
        private long pageSize;
        private long pageIndex;
        private long pageCount;
        private T datas;
    }
}
