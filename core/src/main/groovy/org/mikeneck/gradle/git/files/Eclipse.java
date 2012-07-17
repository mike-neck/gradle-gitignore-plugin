package org.mikeneck.gradle.git.files;

import org.gradle.api.plugins.PluginContainer;
import org.mikeneck.gradle.git.IgnoreFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class Eclipse implements IgnoreFiles {

    private static final String ECLIPSE = "eclipse";

    public static IgnoreFiles git(PluginContainer container) {
        if (container.hasPlugin(ECLIPSE)) {
            return new HasEclipse();
        } else {
            return new NoEclipse();
        }
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

    private static class NoEclipse extends Eclipse {
        @Override
        public List<String> getIgnoreFiles() {
            return Collections.emptyList();
        }
    }
}
