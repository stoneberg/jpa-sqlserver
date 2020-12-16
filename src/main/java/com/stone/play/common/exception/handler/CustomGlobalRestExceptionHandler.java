package com.stone.play.common.exception.handler;

import com.stone.play.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomGlobalRestExceptionHandler {

    // Bad Request
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlerBadRequestException(BadRequestException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.BAD_REQUEST_FOUND, ex.getMessage(), request);
    }

    // User already exists
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ResponseError handleUserExistsException(UserExistsException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.USER_EXISTS, request);
    }

    // Email already in use
    @ExceptionHandler(EmailDuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlerEmailDuplicationException(EmailDuplicationException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.EMAIL_DUPLICATION, request);
    }

    // FileProcessException
    @ExceptionHandler(FileProcessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlerStorageException(FileProcessException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.FILE_PROCESS_FAILED, request);
    }

    // Pdf Generation / Download Exception
    @ExceptionHandler(PdfDownloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlePdfDownloadException(PdfDownloadException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.PDF_DOWNLOAD_FAILED, request);
    }

    // Method not support
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        final List<ResponseError.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(request, fieldErrors);
    }

    // Invalid argument type
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
    	return buildError(ErrorCode.INPUT_VALUE_INVALID, ex.getMessage(), request);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleBindException(BindException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        final List<ResponseError.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(request, fieldErrors);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        final ErrorCode errorCode = ErrorCode.INPUT_VALUE_INVALID;
        final String message = getResultMessage(ex.getConstraintViolations().iterator());
        log.error(errorCode.getMessage(), ex.getConstraintViolations());
        return buildError(errorCode, message, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.INPUT_VALUE_INVALID, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
    	return buildError(ErrorCode.METHOD_NOT_ALLOWED, request);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleHttpMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.FILE_UPLOAD_FAILED, request);
    }

    // JPA, QueryDSL
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.DATA_ACCESS_FAILED, ex.getMessage(), request);
    }

    // User not exists
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ResponseError handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.USER_NOT_FOUND, request);
    }

    // Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ResponseError handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.RESOURCE_NOT_FOUND, ex.getMessage(), request);
    }

    // CommonCode not found
    @ExceptionHandler(CodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    protected ResponseError handlerCodeNotFoundException(CodeNotFoundException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.CODE_NOT_FOUND, request);
    }

    // Authentication failed
    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    protected ResponseError handlerAuthenticationFailedException(AuthenticationFailedException ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.AUTHENTICATION_FAILED, request);
    }

    //    @ExceptionHandler(MailSendException.class)
    //    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    //    protected ResponseError handleMailSendException(MailSendException ex, WebRequest request) {
    //        log.error("@MailSendException::{}", ExceptionUtils.getMessage(ex));
    //        return buildError(ErrorCode.MAIL_SEND_FAILED, request);
    //    }

    // Internal Server Error : 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    protected ResponseError handleAnyException(Exception ex, WebRequest request) {
        log.error(ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.SERVER_EXCEPTION, request);
    }

    private ResponseError buildError(ErrorCode errorCode, WebRequest request) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(errorCode.getMessage())
                .build();
    }
    
    private ResponseError buildError(ErrorCode errorCode, String message, WebRequest request) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(message)
                .build();
    }

    private ResponseError buildFieldErrors(WebRequest request, List<ResponseError.FieldError> errors) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(ErrorCode.INPUT_VALUE_INVALID.getCode())
                .status(ErrorCode.INPUT_VALUE_INVALID.getStatus())
                .path(request.getDescription(false))
                .message(errors.get(0).getReason())
                .errors(errors)
                .build();
    }

    /**
     * find MethodArgumentNotValidException message
     * @param bindingResult
     * @return
     */
    private List<ResponseError.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        if (!errors.isEmpty()) {
            return errors.parallelStream()
                    .map(error -> ResponseError.FieldError.builder()
                            .reason(error.getDefaultMessage())
                            .field(error.getField())
                            .value(error.getRejectedValue() != null ? error.getRejectedValue().toString() : StringUtils.EMPTY)
                            .build())
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * find ConstraintViolationException message
     * @param violationIterator
     * @return
     */
    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                .append("['")
                .append(getPropertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                .append("' is '")
                .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                .append("'. ")
                .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        return resultMessageBuilder.toString();
    }

    protected String getPropertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }    
    
}
