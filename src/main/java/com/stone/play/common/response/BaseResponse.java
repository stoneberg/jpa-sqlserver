package com.stone.play.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    // API 호출 성공 여부
    private String success;
    // API 호출 결과 코드
    private String code;
    // API 호출 결과 메시지
    private String message;
    // 페이징
    private String pagination;

}
