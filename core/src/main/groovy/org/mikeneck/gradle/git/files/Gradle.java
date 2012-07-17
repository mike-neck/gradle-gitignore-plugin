package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.List;

/**
 * @author mike
 */
abstract public class Gradle implements IgnoreFiles {

    public static IgnoreFiles git(PluginContainer container) {
        return new GradleIgnore();
    }

    private static class GradleIgnore extends Gradle {

        private static final List<String> LIST = Arrays.asList(
                "build/", ".gradle"
        );

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }
}
