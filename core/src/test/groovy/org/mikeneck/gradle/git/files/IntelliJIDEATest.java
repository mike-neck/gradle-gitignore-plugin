package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.junit.Test;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class IntelliJIDEATest {

    @Test
    public void intelliJ () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("idea")).andReturn(true);

        replay(container);

        IgnoreFiles ignoreFiles = IntelliJIDEA.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat("*.ipr", isIn(files));
        assertThat("*.iml", isIn(files));
        assertThat("*.iws", isIn(files));
        assertThat(".idea/", isIn(files));
        assertThat("out/", isIn(files));
    }

    @Test
    public void notIntelliJ () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("idea")).andReturn(false);

        replay(container);

        IgnoreFiles ignoreFiles = IntelliJIDEA.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat(files.size(), is(0));
    }
}
