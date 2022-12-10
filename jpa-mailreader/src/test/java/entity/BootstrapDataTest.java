/*
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
package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import junit.framework.TestCase;
import entity.protocol.Protocol;
import entity.protocol.ProtocolImpl;
import entity.subscription.Subscription;
import entity.subscription.SubscriptionImpl;
import entity.user.User;
import entity.user.UserImpl;

public class BootstrapDataTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    public void testTrue() throws Exception {
        assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    public void _testBootstrap() throws Exception {

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction et = null;

        // Test empty (FIXME: Throws exception when actually empty!)
        // List<Protocol> protocols =
        // em.createNamedQuery("Protocol.findAll").getResultList();
        // boolean not_empty = ((protocols != null) && (protocols.size() > 0));
        // assertFalse("Data exists!",not_empty);

        // Start a transaction since we will be updating the database
        et = em.getTransaction();
        et.begin();

        // Create the basic protocol data
        Protocol protocol1 = null;
        protocol1 = new ProtocolImpl();
        protocol1.setId(getUUID());
        protocol1.setDescription("IMAP Protocol");
        em.persist(protocol1);
        Protocol protocol2 = new ProtocolImpl();
        protocol2.setId(getUUID());
        protocol2.setDescription("POP3 Protocol");
        em.persist(protocol2);

        // Set up the initial user and subscriptions
        User user = new UserImpl();
        user.setId(getUUID());
        user.setUsername("user");
        user.setPassword("pass");
        user.setFullName("John Q. User");
        user.setFromAddress("John.User@somewhere.com");
        List<Subscription> list = new ArrayList<Subscription>();
        user.setSubscriptions(list);
        Subscription sub = null;
        sub = new SubscriptionImpl();
        sub.setId(getUUID());
        sub.setUser(user);
        sub.setHost("mail.yahoo.com");
        sub.setProtocol(protocol1);
        sub.setUsername("jquser");
        sub.setPassword("foo");
        list.add(sub);
        sub = new SubscriptionImpl();
        sub.setId(getUUID());
        sub.setUser(user);
        sub.setHost("mail.hotmail.com");
        sub.setProtocol(protocol2);
        sub.setUsername("user1234");
        sub.setPassword("bar");
        list.add(sub);
        em.persist(user);

        // Commit the database updates
        et.commit();

        // Test commit
        List<Protocol> protocols2 = em.createNamedQuery(ProtocolImpl.FIND_ALL)
                .getResultList();
        boolean updated = ((protocols2 != null) && (protocols2.size() > 0));
        assertTrue("Commit not successful!", updated);

        em.close();

    }
}