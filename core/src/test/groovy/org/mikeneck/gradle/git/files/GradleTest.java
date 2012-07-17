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
public class GradleTest {

    @Test
    public void gradle () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("java")).andReturn(true);

        replay(container);

        IgnoreFiles ignoreFiles = Gradle.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat("build/", isIn(files));
        assertThat(".gradle", isIn(files));
    }
}
