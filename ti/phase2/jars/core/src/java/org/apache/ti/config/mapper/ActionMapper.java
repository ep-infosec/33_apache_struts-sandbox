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
package org.apache.ti.config.mapper;

import org.apache.commons.chain.web.WebContext;

/**
 * Handles creation of ActionMapping and reconstruction of URI's from one.
 */
public interface ActionMapper {
	
	/**
	 * @todo Finish documenting me!
	 * 
	 * @param ctx
	 * @return Returns an ActionMapping
	 */
    ActionMapping getMapping(WebContext ctx);

    /**
	 * @todo Finish documenting me!
     * @todo Why does getUri*() return a String and not a Uri?
     * 
     * @param mapping
     * @return Returns the (encoded?) Uri (String) from the ActionMapping
     */
    String getUriFromActionMapping(ActionMapping mapping);
}
