/*
 * B E A   S Y S T E M S
 * Copyright 2002-2004  BEA Systems, Inc.
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
package org.apache.ti.util.xml.validation;


/**
 */
public class SchemaValidationException
        extends RuntimeException {
    public SchemaValidationException() {
        super();
    }

    public SchemaValidationException(String msg) {
        super(msg);
    }

    public SchemaValidationException(Throwable cause) {
        super(cause);
    }

    public SchemaValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
