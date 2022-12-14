/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * An abstract interceptor that provides simple access to before/after callouts.
 *
 * @author Jason Carreira
 * @deprecated AroundInterceptor was removed in Struts 2.
 */
public abstract class AroundInterceptor implements Interceptor {

    protected transient Log log = LogFactory.getLog(getClass());

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        String result = null;

        before(invocation);
        result = invocation.invoke();
        after(invocation, result);

        return result;
    }

    /**
     * Called after the invocation has been executed.
     *
     * @param result the result value returned by the invocation
     */
    protected abstract void after(ActionInvocation dispatcher, String result) throws Exception;

    /**
     * Called before the invocation has been executed.
     */
    protected abstract void before(ActionInvocation invocation) throws Exception;
}