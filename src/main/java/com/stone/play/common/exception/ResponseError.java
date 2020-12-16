package com.stone.play.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseError {

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime timestamp;

	private final String success;

	private final String message;

	private final String code;

	private final int status;

	private final String path;

	@Builder.Default
	private List<FieldError> errors = new ArrayList<>();


	@Builder
	public ResponseError(LocalDateTime timestamp, String success, String message, String code, int status, String path, List<FieldError> errors) {
		this.timestamp = timestamp;
		this.success = success;
		this.message = message;
		this.code = code;
		this.status = status;
		this.path = path;
		this.errors = initErrors(errors);
	}

	private List<FieldError> initErrors(List<FieldError> errors) {
		return (errors == null) ? new ArrayList<>() : errors;
	}

	@Getter
	public static class FieldError {
		private final String field;
		private final String value;
		private final String reason;

		@Builder
		public FieldError(String field, String value, String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}
	}

}
