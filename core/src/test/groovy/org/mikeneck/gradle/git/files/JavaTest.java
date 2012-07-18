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
 * @author mike_neck
 */
public class JavaTest {

    @Test
    public void java () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("java")).andReturn(true);
        replay(container);

        IgnoreFiles ignoreFiles = Java.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat("*.class", isIn(files));
    }

    @Test
    public void notJava () {
        PluginContainer container = createMock(PluginContainer.class);
        expect(container.hasPlugin("java")).andReturn(false);
        replay(container);

        IgnoreFiles ignoreFiles = Java.git(container);
        List<String> files = ignoreFiles.getIgnoreFiles();

        assertThat(files.size(), is(0));
    }
}
