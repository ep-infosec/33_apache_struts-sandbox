/*
 * $Id$
 *
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ti.processor.chain;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.WebContext;
import org.apache.commons.chain.impl.ChainBase;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ti.pageflow.xwork.PageFlowActionContext;
import org.apache.ti.pageflow.handler.Handlers;

/**
 *  Initializes XWork by replacing default factories.
 */
public class ProcessActionChain extends ChainBase {

    private static final Log log = LogFactory.getLog(ProcessActionChain.class);

    public boolean execute(Context origctx) throws Exception {
        WebContext ctx = (WebContext)origctx;
        
        log.debug("Processing action chain");

        ActionProxy proxy = (ActionProxy) ctx.get("actionProxy");
        
        ActionContext nestedContext = ActionContext.getContext();
        ActionContext.setContext(proxy.getInvocation().getInvocationContext());

        boolean retCode = false;
        PageFlowActionContext actionContext = PageFlowActionContext.get();        
        boolean isNestedRequest = actionContext.isNestedRequest();
        

        try {
            retCode = super.execute(origctx);
        } finally {
            // If this is not a nested request, then commit any changes made by the storage handler.
            if ( ! isNestedRequest ) {
                Handlers.get().getStorageHandler().applyChanges();
            }
            
            ActionContext.setContext(nestedContext);
        }
        
        return retCode;
    }
}
