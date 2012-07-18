package org.mikeneck.gradle.git;

import org.gradle.api.plugins.PluginContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class PluginManagerTest {

    private static Properties properties;

    private static Properties testProperties;

    private static final String OS_NAME = "os.name";

    private static final String MAC_OS_X = "Mac OS X";

    private static final String WINDOWS = "Windows";

    private PluginManager manager;

    @BeforeClass
    public static void prepareSystemProperties () {
        properties = System.getProperties();
        testProperties = new Properties();
        for (String key : properties.stringPropertyNames()) {
            if (!OS_NAME.equals(key)) {
                testProperties.setProperty(key, properties.getProperty(key));
            }
        }
    }

    @Before
    public void setup () {
        testProperties.remove(OS_NAME);
        manager = new PluginManager();
    }

    @AfterClass
    public static void recoveryProperties () {
        System.setProperties(properties);
    }

    @Test
    public void macOSX () {
        PluginContainer container = createMock(PluginContainer.class);

        expect(container.hasPlugin("eclipse")).andReturn(true);
        expect(container.hasPlugin("idea")).andReturn(true);
        expect(container.hasPlugin("java")).andReturn(true);

        replay(container);
        testProperties.setProperty(OS_NAME, MAC_OS_X);

        manager.setContainer(container);
        List<IgnoreFiles> filesList = manager.manage();

        assertThat(filesList.size(), is(6));
    }
}
