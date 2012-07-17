package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.junit.Test;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class EclipseTest {

    @Test
    public void eclipse () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("eclipse")).andReturn(true);

        replay(container);

        IgnoreFiles ignoreFiles = Eclipse.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat(".project", isIn(files));
        assertThat(".metadata", isIn(files));
        assertThat("*.pydevproject", isIn(files));
        assertThat("bin/**", isIn(files));
        assertThat("tmp/**", isIn(files));
        assertThat("tmp/**/*", isIn(files));
        assertThat("*.tmp", isIn(files));
        assertThat("*.bak", isIn(files));
        assertThat("*.swp", isIn(files));
        assertThat("*~.nib", isIn(files));
        assertThat("local.properties", isIn(files));
        assertThat(".classpath", isIn(files));
        assertThat(".settings/", isIn(files));
        assertThat(".loadpath", isIn(files));
        assertThat(".externalToolBuilders/", isIn(files));
        assertThat("*.launch", isIn(files));
        assertThat(".cproject", isIn(files));
        assertThat(".buildpath", isIn(files));
    }
}
