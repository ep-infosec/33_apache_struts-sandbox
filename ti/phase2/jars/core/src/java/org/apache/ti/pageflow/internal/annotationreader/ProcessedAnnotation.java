/*
 * Copyright 2005 The Apache Software Foundation.
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
package org.apache.ti.pageflow.internal.annotationreader;


/**
 *
 */
public class ProcessedAnnotation {
    private String _annotationName;
    private AnnotationAttribute[] _annotationAttributes;

    public ProcessedAnnotation() {
    }

    public ProcessedAnnotation(String annotationName, AnnotationAttribute[] annotationAttributes) {
        _annotationName = annotationName;
        _annotationAttributes = annotationAttributes;
    }

    public String getAnnotationName() {
        return _annotationName;
    }

    public void setAnnotationName(String annotationName) {
        _annotationName = annotationName;
    }

    public AnnotationAttribute[] getAnnotationAttributes() {
        return _annotationAttributes;
    }

    public void setAnnotationAttributes(AnnotationAttribute[] annotationAttributes) {
        _annotationAttributes = annotationAttributes;
    }
}
