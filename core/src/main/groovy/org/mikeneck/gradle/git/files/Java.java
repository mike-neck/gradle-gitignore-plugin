package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class Java implements IgnoreFiles {

    public static IgnoreFiles git (PluginContainer container) {
        if (container.hasPlugin("java")) {
            return new IsJava();
        } else {
            return new NotJava();
        }
    }

    private static class IsJava extends Java {

        private static List<String> LIST = Arrays.asList(
                "*.class"
        );

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }

    private static class NotJava extends Java {
        @Override
        public List<String> getIgnoreFiles() {
            return Collections.emptyList();
        }
    }
}
