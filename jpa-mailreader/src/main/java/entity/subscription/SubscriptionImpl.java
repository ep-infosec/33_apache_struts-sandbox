/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// This Java class is based on the "org.apache.mailreaderjpa" class  
// and has been edited to fit this MailReader implementation.   
package entity.subscription;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entity.UuidEntity;
import entity.protocol.Protocol;
import entity.protocol.ProtocolImpl;
import entity.user.User;
import entity.user.UserImpl;

/**
 * <p>
 * JPA entity class for the <code>APP_SUBSCRIPTION</code> table. This class
 * contains sufficient detail to regenerate the database schema (top-down
 * development). The annotation mode is by field.
 * </p>
 */
@NamedQueries( {
        @NamedQuery(name = Subscription.COUNT, query = SubscriptionImpl.COUNT_QUERY),
        @NamedQuery(name = Subscription.FIND_ALL, query = SubscriptionImpl.FIND_ALL_QUERY),
        @NamedQuery(name = Subscription.FIND_BY_NAME, query = SubscriptionImpl.FIND_BY_NAME_QUERY) })
@Entity(name = "SUBSCRIPTION")
@Table(name = "APP_SUBSCRIPTION")
public class SubscriptionImpl extends UuidEntity implements Serializable,
        Subscription {

    // ---- STATICS ----

    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM SUBSCRIPTION";

    private static final String FIND_ALL_QUERY = "SELECT s FROM SUBSCRIPTION s";

    private static final String FIND_BY_NAME_QUERY = "SELECT s FROM SUBSCRIPTION s WHERE s.host = :host";

    // ---- FIELDS ----

    private boolean auto_connect;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private String password;

    @JoinColumn
    @OneToOne(targetEntity = ProtocolImpl.class)
    private Protocol protocol;

    @ManyToOne(targetEntity = UserImpl.class)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String username;

    // ---- PROPERTIES ----

    public boolean isAutoConnect() {
        return auto_connect;
    }

    public void setAutoConnect(boolean value) {
        auto_connect = value;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String value) {
        host = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        password = value;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol value) {
        protocol = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        user = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        username = value;
    }

    // ---- METHODS ----

    /**
     * <p>
     * Instantiate a default <code>Subscription</code> object.
     * </p>
     */
    public SubscriptionImpl() {
        super();
    }

    /**
     * <p>
     * Instantiate a default <code>Subscription</code> object, and load
     * values.
     * </p>
     * 
     */
    public SubscriptionImpl(String host, User user, String username,
            String password, Protocol protocol, boolean autoConnect) {
        super();
        setHost(host);
        setUser(user);
        setUsername(username);
        setPassword(password);
        setProtocol(protocol);
        setAutoConnect(autoConnect);
    }

}
