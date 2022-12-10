/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */
package org.apache.ti.pageflow.handler;

import org.apache.ti.pageflow.internal.AnnotationReader;

/**
 * Handler for reading annotations on a class.
 */
public interface AnnotationHandler extends Handler {

    /**
     * Get a reader for annotations on a class.
     *
     * @param type the class for which to read annotations.
     * @return an AnnotationReader that reads annotations for the given class.
     */
    public AnnotationReader getAnnotationReader(Class type);
}
