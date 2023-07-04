package io.github.ponderyao.sdk.jgpt.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.Request;

/**
 * DefaultRequestInterceptor: default okhttp request interceptor
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class DefaultRequestInterceptor extends AbstractRequestInterceptor {

    public static final Logger log = LoggerFactory.getLogger(DefaultRequestInterceptor.class);

    @Override
    protected void beforeProceedRequest(Request request) {
        log.info("request open api, url is '{}', method is '{}'", request.url(), request.method());
    }
    
}
