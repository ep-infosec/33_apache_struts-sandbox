/*
 * $Id: StrutsSpringObjectFactory.java 817418 2009-09-21 21:36:17Z musachy $
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
package org.apache.struts2.example.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

@ApplicationScoped
public class Generator implements Serializable {
   
   private java.util.Random random = new java.util.Random( System.currentTimeMillis() );
   
   private int maxNumber = 100;
   
   java.util.Random getRandom() {
      return random;
   }
   
   @Produces @Random int next() { 
      return getRandom().nextInt(maxNumber); 
   }
   
   @Produces @MaxNumber int getMaxNumber() {
      return maxNumber;
   }

} 
