package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.List;
import java.util.Properties;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class WindowsTest {

    private static Properties properties;

    private static Properties testProperties;

    private static final String OS_NAME = "os.name";

    private static final String WINDOWS = "Windows";

    @BeforeClass
    public static void exchangeProperties () {
        properties = System.getProperties();
        testProperties = new Properties();
        for (String key : properties.stringPropertyNames()) {
            if (!OS_NAME.equals(key)) {
                testProperties.setProperty(key, properties.getProperty(key));
            }
        }
        System.setProperties(testProperties);
    }

    @AfterClass
    public static void recoveryProperties () {
        System.setProperties(properties);
    }

    @Before
    public void setup () {
        testProperties.remove(OS_NAME);
    }

    @Test
    public void windows () {
        testProperties.setProperty(OS_NAME, "Windows");
        PluginContainer container = createMock(PluginContainer.class);

        expect(container.hasPlugin("java")).andReturn(true);
        replay(container);

        IgnoreFiles ignoreFiles = Windows.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat("Thumbs.db", isIn(files));
        assertThat("Desktop.ini", isIn(files));
        assertThat("$RECYCLE.BIN/", isIn(files));
    }

    @Test
    public void notWindows () {
        testProperties.setProperty(OS_NAME, "Solaris");
        PluginContainer container = createMock(PluginContainer.class);

        expect(container.hasPlugin("java")).andReturn(true);
        replay(container);

        IgnoreFiles ignoreFiles = Windows.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat(files.size(), is(0));
    }
}
