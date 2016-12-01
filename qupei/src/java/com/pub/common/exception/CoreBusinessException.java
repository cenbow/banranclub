package com.pub.common.exception;
import java.util.List;

import com.hercules.database.exception.BusinessException;
public class CoreBusinessException extends BusinessException {

    public CoreBusinessException() {
        super();
    }

    public CoreBusinessException(String errCode, List<String> params) {
        super(errCode, params);
    }

    public CoreBusinessException(String errCode, String[] params) {
        super(errCode, params);
    }

    public CoreBusinessException(String errCode, String message) {
        super(errCode, message);
    }

    public CoreBusinessException(String errCode) {
        super(errCode);
    }
}
