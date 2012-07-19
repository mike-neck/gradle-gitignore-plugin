package org.mikeneck.gradle.git;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.Task;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.PluginContainer;

/**
 * git-ignore task
 *
 * @author mike_neck
 */
class GitIgnorePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        GitIgnoreFile ignoreFile = new GitIgnoreFile(projectHome: project.projectDir)
        ExistingFileLoader loader = new GitIgnoreReader(file: ignoreFile)

        Plugins plugins = new PluginManager(container: project.plugins)

        ExtensionContainer extensions = project.getExtensions()
        GitIgnore gitIgnore = extensions.create(GitIgnore.extension, GitIgnore.class)

        def writer = GitIgnoreMerger
                .init(loader)
                .plugins(plugins)
                .merge(gitIgnore)

        project.task(GitIgnore.extension).doLast {writer.closure ()}
    }

}
