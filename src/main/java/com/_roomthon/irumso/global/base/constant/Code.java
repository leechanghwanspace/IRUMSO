package com._roomthon.irumso.global.base.constant;

import com._roomthon.irumso.global.base.GeneralException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum Code {

    OK("0000", HttpStatus.OK, "Ok"),

    /*
     * Request 관련 오류
     * HEAD NAME - RQ (Request)
     * */
    BAD_REQUEST("RQ000", HttpStatus.BAD_REQUEST, "Bad request"),
    VALIDATION_ERROR("RQ001", HttpStatus.BAD_REQUEST, "Validation error"),
    LANGUAGE_NOT_SUPPORTED("RQ002", HttpStatus.BAD_REQUEST, "Language not supported"),

    /*
     * Member 관련 오류
     * HEAD NAME - M (Member)
     * */
    DUPLICATED_NICKNAME("M001", HttpStatus.BAD_REQUEST, "Duplicate nickname"),
    MEMBER_NOT_FOUND("M002", HttpStatus.NOT_FOUND, "Member not found"),
    MEMBER_WITHOUT_PRIVILEGE("M003", HttpStatus.FORBIDDEN, "Member without privilege"),

    /*
     * 서버 관련 오류
     * HEAD NAME - S (Server)
     * */
    INTERNAL_ERROR("S000", HttpStatus.INTERNAL_SERVER_ERROR, "Internal error"),
    KAKAO_SERVER_ERROR("S001", HttpStatus.INTERNAL_SERVER_ERROR , "Kakao server error"),

    /*
     * 인증 관련 오류
     * HEAD NAME - AUTH (Authorization)
     * */
    UNAUTHORIZED("AUTH000", HttpStatus.UNAUTHORIZED, "User unauthorized"),
    NOT_REGISTERED("AUTH001", HttpStatus.OK, "Need registration"),
    ALREADY_REGISTERED("AUTH002", HttpStatus.BAD_REQUEST, "You're already registered"),
    INVALID_REFRESH_TOKEN("AUTH003", HttpStatus.UNAUTHORIZED, "Invalid refresh token. Sign in again"),
    REFRESH_TOKEN_NOT_FOUND("AUTH004", HttpStatus.UNAUTHORIZED, "Refresh token not found. Sign in again"),
    MALFORMED_JWT("AUTH005", HttpStatus.UNAUTHORIZED, "Malformed jwt format"),
    EXPIRED_JWT("AUTH006", HttpStatus.UNAUTHORIZED, "Jwt expired. Reissue it"),
    UNSUPPORTED_JWT("AUTH007", HttpStatus.UNAUTHORIZED, "Unsupported jwt format"),
    ILLEGAL_JWT("AUTH008", HttpStatus.UNAUTHORIZED, "Illegal jwt format"),
    FORBIDDEN("AUTH009", HttpStatus.FORBIDDEN, "Forbidden"),
    NO_CREDENTIALS_IN_CONTEXT("AUTH010", HttpStatus.UNAUTHORIZED, "No credentials in Security Context")
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
        // 결과 예시 - "Validation error - Reason why it isn't valid"
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    public static Code valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getHttpStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) {
                        return Code.BAD_REQUEST;
                    } else if (httpStatus.is5xxServerError()) {
                        return Code.INTERNAL_ERROR;
                    } else {
                        return Code.OK;
                    }
                });
    }
}