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
package org.apache.ti.compiler.internal.typesystem.impl.declaration;

import org.apache.ti.compiler.internal.typesystem.declaration.Declaration;
import org.apache.ti.compiler.internal.typesystem.declaration.TypeParameterDeclaration;
import org.apache.ti.compiler.internal.typesystem.impl.WrapperFactory;
import org.apache.ti.compiler.internal.typesystem.type.ReferenceType;

import java.util.Collection;

public class TypeParameterDeclarationImpl
        extends DeclarationImpl
        implements TypeParameterDeclaration {

    private ReferenceType[] _bounds;

    public TypeParameterDeclarationImpl(com.sun.mirror.declaration.TypeParameterDeclaration delegate) {
        super(delegate);
    }

    public ReferenceType[] getBounds() {
        if (_bounds == null) {
            Collection<com.sun.mirror.type.ReferenceType> delegateCollection = getDelegate().getBounds();
            ReferenceType[] array = new ReferenceType[delegateCollection.size()];
            int j = 0;
            for (com.sun.mirror.type.ReferenceType i : delegateCollection) {
                array[j++] = WrapperFactory.get().getReferenceType(i);
            }
            _bounds = array;
        }

        return _bounds;
    }

    public Declaration getOwner() {
        return WrapperFactory.get().getDeclaration(getDelegate().getOwner());
    }

    protected com.sun.mirror.declaration.TypeParameterDeclaration getDelegate() {
        return (com.sun.mirror.declaration.TypeParameterDeclaration) super.getDelegate();
    }
}
