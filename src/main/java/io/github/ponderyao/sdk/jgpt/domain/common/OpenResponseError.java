package io.github.ponderyao.sdk.jgpt.domain.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * OpenResponseError: error message from open api response
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenResponseError implements Serializable {
    
    private static final long serialVersionUID = -3532176186123386951L;
    
    private String message;
    
    private String type;
    
    private String param;
    
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
