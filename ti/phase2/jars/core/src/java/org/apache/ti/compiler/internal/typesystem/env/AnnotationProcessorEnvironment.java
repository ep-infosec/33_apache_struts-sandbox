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
package org.apache.ti.compiler.internal.typesystem.env;

import org.apache.ti.compiler.internal.typesystem.declaration.AnnotationTypeDeclaration;
import org.apache.ti.compiler.internal.typesystem.declaration.Declaration;
import org.apache.ti.compiler.internal.typesystem.declaration.TypeDeclaration;

import java.util.Map;

public interface AnnotationProcessorEnvironment {

    /**
     * Map of String -> String
     */
    Map getOptions();

    Messager getMessager();

    Filer getFiler();

    TypeDeclaration[] getSpecifiedTypeDeclarations();

    TypeDeclaration getTypeDeclaration(String s);

    Declaration[] getDeclarationsAnnotatedWith(AnnotationTypeDeclaration annotationTypeDeclaration);

    void setAttribute(String propertyName, Object value);

    Object getAttribute(String propertyName);
}
