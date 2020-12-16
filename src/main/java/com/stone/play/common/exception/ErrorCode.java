package com.stone.play.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

	USER_NOT_FOUND("FAILURE_001", "등록된 사용자가 아닙니다.", HttpStatus.NOT_FOUND.value()),
	USER_EXISTS("FAILURE_002", "이미 등록된 사용자 입니다.", HttpStatus.BAD_REQUEST.value()),
	EMAIL_DUPLICATION("FAILURE_003", "사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST.value()),
	AUTHENTICATION_FAILED("FAILURE_004", "아이디와 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED.value()),
	JWT_TOKEN_INVALID("FAILURE_005", "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED.value()),
	INPUT_VALUE_INVALID("FAILURE_006", "입력 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST.value()),
	METHOD_NOT_ALLOWED("FAILURE_007","메서드 요청형식(GET, POST, PUT, DELETE)이 정확하지 않습니다.", HttpStatus.METHOD_NOT_ALLOWED.value()),
	RESOURCE_NOT_FOUND("FAILURE_008","요청한 자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	CODE_NOT_FOUND("FAILURE_009","요청한 코드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	BAD_REQUEST_FOUND("FAILURE_010","요청 사항이 누락되었거나 잘못되었습니다.", HttpStatus.BAD_REQUEST.value()),
	NOT_RESOURCE_OWNER("FAILURE_011", "요청한 자원의 소유자가 아닙니다.", HttpStatus.BAD_REQUEST.value()),
	FILE_UPLOAD_FAILED("FAILURE_012", "개별 파일 용량이 30MB를 초과한 경우 업로드할 수 없습니다.", HttpStatus.BAD_REQUEST.value()),
	DATA_ACCESS_FAILED("FAILURE_013", "데이터 조회 시 오류가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	PDF_DOWNLOAD_FAILED("FAILURE_014", "PDF 다운로드 시 오류가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	FILE_PROCESS_FAILED("FAILURE_015", "파일 처리 시 문제가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	// MAIL_SEND_FAILED("FAILURE_016", "메일 전송 시 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	MAIL_SEND_FAILED("FAILURE_016", "업무 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	SERVER_EXCEPTION("SERVER_FAILURE","서버 오류가 발생했습니다. 관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	private final String code;
	private final String message;
	private final int status;

	ErrorCode(String code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

}
