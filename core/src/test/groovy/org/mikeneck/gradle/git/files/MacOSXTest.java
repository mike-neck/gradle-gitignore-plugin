package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mikeneck.gradle.git.IgnoreFiles;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.hamcrest.Matchers.contains;

/**
 * @author mike
 */
@RunWith (PowerMockRunner.class)
@PrepareForTest ({System.class})
public class MacOSXTest {

    @Test
    public void macOSX () {
        mockStatic(System.class);
        PluginContainer container = createMock(PluginContainer.class);

        expect(System.getProperty("os.name")).andReturn("Mac OS X");
        expect(container.hasPlugin("java")).andReturn(true);

        replay(System.class, container);

        IgnoreFiles ignoreFiles = MacOSX.git(container);

        List<String> files = ignoreFiles.getIgnoreFiles();
        assertThat(".DS_Store", isIn(files));
        assertThat("._*", isIn(files));
        assertThat(".Spotlight-V100", isIn(files));
        assertThat(".Trashes", isIn(files));
    }

}
