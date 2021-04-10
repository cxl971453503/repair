package com.chenxile.repair.exception;

import com.chenxile.repair.util.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;



@Getter
public class CmsException extends RuntimeException {
    private StatusCode statusCode;

    public CmsException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public CmsException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CmsException(String message, Throwable cause, StatusCode statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public CmsException(Throwable cause, StatusCode statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
