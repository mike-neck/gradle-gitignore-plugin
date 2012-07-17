package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.List;

/**
 * @author mike
 */
abstract public class Eclipse implements IgnoreFiles {

    public static IgnoreFiles git(PluginContainer container) {
        return new HasEclipse();
    }

    private static class HasEclipse extends Eclipse {

        private static final List<String> LIST = Arrays.asList(
                "*.pydevproject", ".project", ".metadata", "bin/**", "tmp/**",
                "tmp/**/*", "*.tmp", "*.bak", "*.swp", "*~.nib", "local.properties",
                ".classpath", ".settings/", ".loadpath", ".externalToolBuilders/",
                "*.launch", ".cproject", ".buildpath"
        );

        @Override
        public List<String> getIgnoreFiles() {
            return LIST;
        }
    }
}
