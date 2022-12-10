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
package action;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.ConversionType;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

import entity.EntityAware;

/**
 * <p>
 * The top-level Struts-specific business and persistance logic API for the
 * MailReader application.
 * </p>
 * <p>
 * The application is entity or resource-orientated. Subclasses provide
 * additional logic specific to each entity or resource. Persistence system
 * logic is delegated to a service class for each entity. The Action classes
 * interact only with the entity helpers, and not the underlying JPA.
 * </p>
 * <p>
 * Any helper method that is not physically bound the the UI API should be moved
 * to the service layer! In most cases, the remaining Action methods will either
 * call ActionErrors, return a result value meaningful only to the UI, or retain
 * state used by other Action helper methods. (A generic ServiceError class with
 * an ActionError adapter might allow us to move 3/4 of the Action helper
 * methods to the service layer.)
 * </p>
 */
@Conversion(conversions = {
        @TypeConversion(type = ConversionType.APPLICATION, key = "entity.protocol.Protocol", converter = "entity.protocol.ProtocolTypeConverter"),
        @TypeConversion(type = ConversionType.APPLICATION, key = "entity.subscription.Subscription", converter = "entity.subscription.SubscriptionTypeConverter"),
        @TypeConversion(type = ConversionType.APPLICATION, key = "entity.user.User", converter = "entity.user.UserTypeConverter") })
@SuppressWarnings("unchecked")
@ParentPackage("entity-default")
public class Index extends ActionSupport implements EntityAware, SessionAware {

    // ---- STATICS ----

    // General Struts statics
    public static final String CANCEL = "cancel";
    public static final String INDEX = "index";
    public static final String NAMESPACE = "namespace";
    public static final String NONE = "";
    public static final String PROFILE_KEY = "ACTION_PROFILE";
    public static final String REDIRECT_ACTION = "redirect-action";
    public static final String UPDATE_INPUT = "update!input";

    // MailReader-specific statics
    public static final String NS_USER = "/user";
    public static final String NS_SUBSCRIPTION = "/user/subscription";
    public static final String SUBSCRIPTION_USER_USERNAME = "${subscription.user.username}";
    public static final String USER = "user";
    public static final String USER_USERNAME = "${user.username}";

    /**
     * <p>
     * Indicate pending "create" operation in a CRUD workflow.
     * </p>
     */
    protected static final String CREATE = "create";

    /**
     * <p>
     * Indicate pending "delete" operation in a CRUD workflow.
     * </p>
     */
    protected static final String DELETE = "delete";

    /**
     * <p>
     * Indicate pending "update" operation in a CRUD workflow.
     * </p>
     */
    protected static final String UPDATE = "update";

    // ---- PROPERTIES ----

    /**
     * <p>
     * Provide a local cache of client properties.
     * </p>
     */
    public Map getProfile() {
        return (Map) getSession().get(PROFILE_KEY);
    }

    public void setProfile(Map value) {
        getSession().put(PROFILE_KEY, value);
    }

    private String input = CREATE;

    /**
     * <p>
     * Access the CRUD operation token.
     * </p>
     */
    public String getInput() {
        return input;
    }

    /**
     * <p>
     * Store the CRUD operation in token.
     * </p>
     */
    public void setInput(String value) {
        input = value;
    }

    private EntityManager entityManager;

    /**
     * <p>
     * Store the session context, or its proxy.
     * </p>
     */
    public void setEntityManager(EntityManager value) {
        entityManager = value;
    }

    /**
     * <p>
     * Access the EntityManager for this thread.
     * </p>
     */
    protected EntityManager getManager() {
        return entityManager;
    }

    private Map session;

    /**
     * <p>
     * Store the session context, or its proxy.
     * </p>
     */
    public void setSession(Map value) {
        session = value;
        Object profile = getProfile();
        if (profile == null) {
            setProfile(new HashMap());
        }
    }

    /**
     * <p>
     * Access the session context, or its proxy.
     * </p>
     */
    protected Map getSession() {
        return session;
    }

    // ---- METHODS ----

    /**
     * <p>
     * Signal an early exit from the pending workflow.
     * </p>
     */
    public String cancel() {
        return CANCEL;
    }

    /**
     * <p>
     * Indicate whether there are no errors.
     * </p>
     */
    public boolean NotErrors() {
        return !hasErrors();
    }

}
