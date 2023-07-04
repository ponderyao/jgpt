package io.github.ponderyao.sdk.jgpt.domain.common;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * OpenResponse: open api response
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenResponse<T> implements Serializable {

    private static final long serialVersionUID = -402498515532088499L;
    
    private String object;
    
    private List<T> data;
    
    private OpenResponseError error;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public OpenResponseError getError() {
        return error;
    }

    public void setError(OpenResponseError error) {
        this.error = error;
    }
}
