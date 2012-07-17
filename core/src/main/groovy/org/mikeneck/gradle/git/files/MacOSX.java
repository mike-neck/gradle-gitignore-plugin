package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class MacOSX implements IgnoreFiles {

    private static final String MAC_OS_X = "Mac OS X";

    private static final String PROPERTY_ID = "os.name";

    public static IgnoreFiles git(PluginContainer container) {
        String osName = System.getProperty(PROPERTY_ID);
        if (osName.startsWith(MAC_OS_X)) {
            return new IsMac();
        } else {
            return new NotMac();
        }
    }

    private static class IsMac extends MacOSX {

        private static final List<String> LIST = Arrays.asList(
                new String[]{
                    ".DS_Store",
                    "._*",
                    ".Spotlight-V100",
                    ".Trashes"});

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }

    private static class NotMac extends MacOSX {
        @Override
        public List<String> getIgnoreFiles() {
            return Collections.emptyList();
        }
    }
}
