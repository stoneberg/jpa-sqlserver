package com.stone.play.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * AipReponse Util
 * @author LYP
 */
@Getter
@Setter
public class ResponseDto<T> extends BaseResponse {

    /**
     * return result response dto
     */
    // API 호출 결과 Data
    private T data;

    /**
     * SUCCESS
     * return response data with success code, message
     *
     * @param data
     * @return
     */
    public static <T> ResponseDto<T> of(T data) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(data); // response payload
        response.setSuccess(Boolean.TRUE.toString());
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    /**
     * SUCCESS
     * return response data with success code, message
     *
     * @param data
     * @param pagination
     * @return
     */
    public static <T> ResponseDto<T> of(T data, String pagination) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(data); // response payload
        response.setSuccess(Boolean.TRUE.toString());
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setPagination(pagination);
        return response;
    }


    /**
     * SUCCESS
     * return succes status code, message only
     *
     * @return
     */
    public static ResponseDto<Object> ok() {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(Boolean.TRUE.toString());
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    /**
     * SUCCESS
     * return succes status with custom message
     *
     * @param message
     * @return
     */
    public static ResponseDto<Object> ok(String message) {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(Boolean.TRUE.toString());
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(message);
        return response;
    }

    /**
     * fail
     *
     * @return
     */
    public static ResponseDto<Object> fail() {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(Boolean.FALSE.toString());
        response.setCode(ResponseCode.FAILURE.getCode());
        response.setMessage(ResponseCode.FAILURE.getMessage());
        return response;
    }

    /**
     * fail
     * return custom failure message
     *
     * @param message
     * @return
     */
    public static ResponseDto<Object> fail(String message) {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(Boolean.FALSE.toString());
        response.setCode(ResponseCode.FAILURE.getCode());
        response.setMessage(message);
        return response;
    }

}
