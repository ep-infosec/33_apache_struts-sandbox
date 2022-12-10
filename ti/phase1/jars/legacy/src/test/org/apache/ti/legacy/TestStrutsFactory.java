package org.apache.ti.legacy;

import junit.framework.*;
import com.opensymphony.xwork.config.providers.XmlConfigurationProvider;
import com.opensymphony.xwork.config.ConfigurationManager;
import com.opensymphony.xwork.config.Configuration;
import com.opensymphony.xwork.config.ConfigurationProvider;
import com.opensymphony.xwork.config.entities.PackageConfig;
import com.opensymphony.xwork.config.entities.ResultConfig;
import com.opensymphony.xwork.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork.ActionSupport;
import org.apache.struts.action.*;
import org.apache.struts.config.*;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Test of StrutsFactory, which creates Struts 1.x wrappers around XWork config objects.
 */
public class TestStrutsFactory extends TestCase {

    private static final String PACKAGE_NAME = "/org/apache/ti/legacy";
    
    protected StrutsFactory factory = null;
    protected Configuration config;

    public TestStrutsFactory(String name) throws Exception {
        super(name);
    }


    public static void main(String args[]) {
        junit.textui.TestRunner.run(TestStrutsFactory.class);
    }

    /**
     * This allows running of the test within an IDE; the ConfigurationProvider can accept a root directory specified
     * in a System property, to avoid having to depend on loading a resource through ClassLoader.
     */
    private class TestConfigurationProvider extends XmlConfigurationProvider {

        public TestConfigurationProvider(String filename) {
            super(filename);
        }

        protected InputStream getInputStream(String fileName) {
            String rootDir = System.getProperty("configRootDir");
            try {
                return rootDir != null ? new FileInputStream(rootDir + fileName) : super.getInputStream(fileName);
            } catch (IOException e) {
                fail(e.getMessage());
                return null;
            }
        }
    }

    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() {
        ConfigurationProvider provider = new TestConfigurationProvider(PACKAGE_NAME + "/test-struts-factory.xml");
        ConfigurationManager.addConfigurationProvider(provider);
        config = ConfigurationManager.getConfiguration();
        factory = StrutsFactory.getStrutsFactory();
    }

    /**
     * Test the creation of a Struts 1.x ModuleConfig wrapper around an XWork PackageConfig.
     * The PackageConfig is loaded from test-struts-factory.xml.
     */
    public void testCreateModuleConfig() {
        ModuleConfig moduleConfig = factory.createModuleConfig(PACKAGE_NAME);
        assertNotNull(moduleConfig);
        
        assertEquals(PACKAGE_NAME, moduleConfig.getPrefix());
        
        ActionConfig actionConfig = moduleConfig.findActionConfig("/action1");
        assertNotNull(actionConfig);
        assertEquals("/action1", actionConfig.getPath());
        
        ActionConfig[] actionConfigs = moduleConfig.findActionConfigs();
        assertNotNull(actionConfigs);
        assertEquals(2, actionConfigs.length);
        
        ExceptionConfig exceptionConfig = moduleConfig.findExceptionConfig(Exception.class.getName());
        assertNotNull(exceptionConfig);
        assertEquals(Exception.class.getName(), exceptionConfig.getType());
        
        ExceptionConfig[] exceptionConfigs = moduleConfig.findExceptionConfigs();
        assertNotNull(exceptionConfigs);
        assertEquals(1, exceptionConfigs.length);
        
        ForwardConfig fwdConfig = moduleConfig.findForwardConfig("globalResult");
        assertNotNull(fwdConfig);
        assertEquals("globalResult", fwdConfig.getName());
        
        // These methods are currently not implemented -- replace as functionality is added.
        assertNYI(moduleConfig, "getControllerConfig", null);
        assertNYI(moduleConfig, "getActionFormBeanClass", null);
        assertNYI(moduleConfig, "getActionMappingClass", null);
        assertNYI(moduleConfig, "getActionForwardClass", null);
        assertNYI(moduleConfig, "findDataSourceConfig", String.class);
        assertNYI(moduleConfig, "findDataSourceConfigs", null);
        assertNYI(moduleConfig, "findFormBeanConfig", String.class);
        assertNYI(moduleConfig, "findFormBeanConfigs", null);
        assertNYI(moduleConfig, "findMessageResourcesConfig", String.class);
        assertNYI(moduleConfig, "findMessageResourcesConfigs", null);
        assertNYI(moduleConfig, "findPlugInConfigs", null);
    }
    
    /**
     * Test the creation of a Struts 1.x ActionMapping wrapper around an XWork ActionConfig.
     * The ActionConfig is loaded from test-struts-factory.xml.
     */
    public void testCreateActionMapping() {
        PackageConfig packageConfig = config.getPackageConfig(PACKAGE_NAME);
        com.opensymphony.xwork.config.entities.ActionConfig actionConfig =
                (com.opensymphony.xwork.config.entities.ActionConfig) packageConfig.getActionConfigs().get("action1");
        ActionMapping mapping = factory.createActionMapping(actionConfig);
        assertNotNull(mapping);

        assertNotNull(mapping.findForward("result1"));
        assertNotNull(mapping.findForwardConfig("result2"));

        ForwardConfig[] configs = mapping.findForwardConfigs();
        assertNotNull(configs);
        assertEquals(2, configs.length);

        String[] forwards = mapping.findForwards();
        assertNotNull(forwards);
        assertEquals(2, forwards.length);
        
        ActionForward fwd = mapping.findForward("result1");
        assertNotNull(fwd);
        assertEquals("result1", fwd.getName());

        assertNotNull(mapping.findException(NullPointerException.class));
        assertNotNull(mapping.findExceptionConfig("java.lang.IllegalStateException"));

        ExceptionConfig[] exceptionConfigs = mapping.findExceptionConfigs();
        assertNotNull(exceptionConfigs);
        assertEquals(3, exceptionConfigs.length);
        
        ModuleConfig moduleConfig = mapping.getModuleConfig();
        assertNotNull(moduleConfig);
        
        // For now, the path will be null if the ActionMapping was created on its own (as opposed to from a
        // WrapperModuleConfig, which knows the path).
        assertNull(mapping.getPath());
        
        // These methods are currently not implemented -- replace as functionality is added.
        assertNYI(mapping, "getInputForward", null);
        assertNYI(mapping, "getAttribute", null);
        assertNYI(mapping, "getForward", null);
        assertNYI(mapping, "getInclude", null);
        assertNYI(mapping, "getInput", null);
        assertNYI(mapping, "getMultipartClass", null);
        assertNYI(mapping, "getName", null);
        assertNYI(mapping, "getParameter", null);
        assertNYI(mapping, "getPrefix", null);
        assertNYI(mapping, "getRoles", null);
        assertNYI(mapping, "getRoleNames", null);
        assertNYI(mapping, "getScope", null);
        assertNYI(mapping, "getSuffix", null);
        assertNYI(mapping, "getType", null);
        assertNYI(mapping, "getUnknown", null);
        assertNYI(mapping, "getValidate", null);
    }

    /**
     * Test the creation of a Struts 1.x ActionForward wrapper around an XWork ResultConfig.
     * The ResultConfig is loaded from test-struts-factory.xml.
     */
    public void testCreateActionForward() {
        PackageConfig packageConfig = config.getPackageConfig(PACKAGE_NAME);
        ResultConfig resultConfig = (ResultConfig) packageConfig.getGlobalResultConfigs().get("globalResult");
        ActionForward fwd = factory.createActionForward(resultConfig);
        assertNotNull(fwd);
        assertEquals("globalResult", fwd.getName());
        
        // These methods are currently not implemented -- replace as functionality is added.
        assertNYI(fwd, "getPath", null);
        assertNYI(fwd, "getModule", null);
        assertNYI(fwd, "getRedirect", null);
    }

    /**
     * Test the creation of a Struts 1.x ExceptionConfig wrapper around an XWork ExceptionHandlerConfig.
     * The ExceptionConfig is loaded from test-struts-factory.xml.
     */
    public void testCreateExceptionConfig() {
        PackageConfig packageConfig = config.getPackageConfig(PACKAGE_NAME);
        ExceptionMappingConfig cfg = (ExceptionMappingConfig) packageConfig.getGlobalExceptionMappingConfigs().get(0);
        ExceptionConfig exceptionConfig = factory.createExceptionConfig(cfg);
        assertNotNull(exceptionConfig);
        assertEquals(Exception.class.getName(), exceptionConfig.getType());

        assertNYI(exceptionConfig, "getBundle", null);
        assertNYI(exceptionConfig, "getHandler", null);
        assertNYI(exceptionConfig, "getKey", null);
        assertNYI(exceptionConfig, "getPath", null);
        assertNYI(exceptionConfig, "getScope", null);
    }

    public void testConvertErrors() throws Exception {

        ActionMessage err1 = new ActionMessage("error1");
        ActionMessage err2 = new ActionMessage("error2", new Integer(1));
        ActionErrors errors = new ActionErrors();
        errors.add(errors.GLOBAL_MESSAGE, err1);
        errors.add("foo", err2);

        ActionSupport action = new ActionSupport();
        factory.convertErrors(errors, action);

        assertTrue(1 == action.getActionErrors().size());
        assertTrue(1 == action.getFieldErrors().size());
    }

    /**
     * Assert that the given method throws UnsupportedOperationException.
     */
    private void assertNYI(Object o, String methodName, Class argType) {
        try {
            Class[] argTypes = argType != null ? new Class[]{argType} : null;
            Object[] args = argType != null ? new Object[]{argType.newInstance()} : null;
            o.getClass().getMethod(methodName, argTypes).invoke(o, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            assertEquals(cause.getMessage(), UnsupportedOperationException.class, cause.getClass());
            
            // OK -- it's what we expected
            return;
        } catch (Exception e) {
            fail(e.getClass().getName() + ": " + e.getMessage());
        }

        fail("Expected UnsupportedOperationException for " + methodName + "() on " + o.getClass().getName());
    }
}
