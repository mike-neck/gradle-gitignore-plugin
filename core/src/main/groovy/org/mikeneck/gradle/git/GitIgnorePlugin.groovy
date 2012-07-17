package org.mikeneck.gradle.git

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.plugins.PluginContainer

/**
 * git-ignore task
 *
 * @author mike_neck
 */
class GitIgnorePlugin implements Plugin<Project> {

    private static final String GIT_IGNORE = '.gitignore'

    @Override
    void apply(Project project) {

        project.task('git-ignore').doLast {
        }
    }
}
