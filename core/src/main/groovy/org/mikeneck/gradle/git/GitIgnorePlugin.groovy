package org.mikeneck.gradle.git

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * git-ignore task
 *
 * @author mike_neck
 */
class GitIgnorePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('git-ignore').doLast {
            println "java    : ${project.plugins.hasPlugin('java')}"
            println "groovy  : ${project.plugins.hasPlugin('groovy')}"
            println "eclipse : ${project.plugins.hasPlugin('eclipse')}"
            println "idea    : ${project.plugins.hasPlugin('idea')}"
        }
    }
}
