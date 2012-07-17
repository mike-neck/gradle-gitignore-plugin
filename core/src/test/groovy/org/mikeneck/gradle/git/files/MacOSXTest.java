package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mikeneck.gradle.git.IgnoreFiles;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Properties;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.hamcrest.Matchers.contains;

/**
 * @author mike
 */
public class MacOSXTest {

    private static Properties properties;

    private static Properties testProperties;

    private static final String OS_NAME = "os.name";

    private static final String MAC_OS_X = "Mac OS X";

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
    public void macOSX () {
        testProperties.setProperty(OS_NAME, MAC_OS_X);

        PluginContainer container = createMock(PluginContainer.class);

        expect(container.hasPlugin("java")).andReturn(true);
        replay(System.class, container);

        IgnoreFiles ignoreFiles = MacOSX.git(container);

        List<String> files = ignoreFiles.getIgnoreFiles();
        assertThat(".DS_Store", isIn(files));
        assertThat("._*", isIn(files));
        assertThat(".Spotlight-V100", isIn(files));
        assertThat(".Trashes", isIn(files));
    }

    @Test
    public void windows () {
        testProperties.setProperty(OS_NAME, "Windows");

        PluginContainer container = createMock(PluginContainer.class);

        expect(container.hasPlugin("java")).andReturn(true);
        replay(System.class, container);

        IgnoreFiles ignoreFiles = MacOSX.git(container);

        List<String> files = ignoreFiles.getIgnoreFiles();
        assertThat(files.size(), is(0));
    }
}
