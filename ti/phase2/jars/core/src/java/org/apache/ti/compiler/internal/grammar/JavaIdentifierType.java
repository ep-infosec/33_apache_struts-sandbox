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
package org.apache.ti.compiler.internal.grammar;

import org.apache.ti.compiler.internal.AnnotationGrammar;
import org.apache.ti.compiler.internal.AnnotationMemberType;
import org.apache.ti.compiler.internal.typesystem.declaration.AnnotationInstance;
import org.apache.ti.compiler.internal.typesystem.declaration.AnnotationTypeElementDeclaration;
import org.apache.ti.compiler.internal.typesystem.declaration.AnnotationValue;
import org.apache.ti.compiler.internal.typesystem.declaration.MemberDeclaration;

public class JavaIdentifierType
        extends AnnotationMemberType {

    private char[] _validChars;

    public JavaIdentifierType(String requiredRuntimeVersion, AnnotationGrammar parentGrammar, char[] validChars) {
        super(requiredRuntimeVersion, parentGrammar);
        _validChars = validChars;
    }


    public Object onCheck(AnnotationTypeElementDeclaration valueDecl, AnnotationValue value,
                          AnnotationInstance[] parentAnnotations, MemberDeclaration classMember,
                          int annotationArrayIndex) {
        String val = (String) value.getValue();

        if (val.length() > 0) {
            char firstChar = val.charAt(0);

            if (! Character.isJavaIdentifierStart(firstChar)) {
                Object[] args = new Object[]{new Character(firstChar)};
                addError(value, "error.invalid-java-identifier-start", args);
            }

            for (int i = 1; i < val.length(); i++) {
                char c = val.charAt(i);

                if (! Character.isJavaIdentifierPart(val.charAt(i)) && ! isValid(c)) {
                    Object[] args = new Object[]{new Character(c)};
                    addError(value, "error.invalid-java-identifier-part", args);
                }
            }
        }

        return null;
    }

    private boolean isValid(char c) {
        if (_validChars != null) {
            for (int i = 0; i < _validChars.length; i++) {
                if (c == _validChars[i]) return true;
            }
        }

        return false;
    }
}
