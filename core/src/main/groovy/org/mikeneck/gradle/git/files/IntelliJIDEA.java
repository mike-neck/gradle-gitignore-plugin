package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class IntelliJIDEA implements IgnoreFiles {

    public static IgnoreFiles git(PluginContainer container) {
        if (container.hasPlugin("idea")) {
            return new HasIntelliJ();
        } else {
            return new NoIntelliJ();
        }
    }

    private static class HasIntelliJ extends IntelliJIDEA {

        private static final List<String> LIST = Arrays.asList(
                new String[] {
                        "*.ipr",
                        "*.iml",
                        "*.iws",
                        ".idea/",
                        "out/"
                });

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }

    private static class NoIntelliJ extends IntelliJIDEA {
        @Override
        public List<String> getIgnoreFiles() {
            return Collections.emptyList();
        }
    }
}
