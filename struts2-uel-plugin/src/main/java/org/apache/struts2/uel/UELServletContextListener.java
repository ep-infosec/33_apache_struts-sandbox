/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.uel;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.tree.TreeBuilder;

import javax.el.ExpressionFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 * Responsible for creating the ExpressionFactory that will be used by the
 * UelValueStack
 */
public class UELServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent contextEvent) {
        Properties juelProperties = new Properties();
        juelProperties.setProperty("javax.el.methodInvocations", "true");

        //custom parser
        juelProperties.setProperty(TreeBuilder.class.getName(), JUELExtensionBuilder.class.getName());

        ExpressionFactory factory = new ExpressionFactoryImpl(juelProperties);

        ExpressionFactoryHolder.setExpressionFactory(factory);
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
    }
}
