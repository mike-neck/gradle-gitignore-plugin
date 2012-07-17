package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class Windows implements IgnoreFiles {

    private static final String OS_NAME = "os.name";

    private static final String WINDOWS = "Windows";

    public static IgnoreFiles git (PluginContainer container) {
        if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
            return new IsWindows();
        } else {
            return new IsNotWindows();
        }
    }

    private static class IsWindows extends Windows {

        private static final List<String> LIST = Arrays.asList(
                "Thumbs.db", "Desktop.ini", "$RECYCLE.BIN/"
        );

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }

    private static class IsNotWindows extends Windows {
        @Override
        public List<String> getIgnoreFiles() {
            return Collections.emptyList();
        }
    }
}
