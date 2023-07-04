package io.github.ponderyao.sdk.jgpt;

import io.github.ponderyao.sdk.jgpt.listener.AbstractStreamESListener;

/**
 * TestEventSourceListener
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class TestEventSourceListener extends AbstractStreamESListener {

    @Override
    public void processEvent(String eventMessage) {
        System.out.println(eventMessage);
    }

    @Override
    public void processError(Throwable error, String errorMessage) {
        System.out.println(errorMessage);
    }
}
