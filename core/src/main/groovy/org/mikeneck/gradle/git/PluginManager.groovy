package org.mikeneck.gradle.git

import org.gradle.api.plugins.PluginContainer
import org.mikeneck.gradle.git.files.Eclipse
import org.mikeneck.gradle.git.files.Gradle
import org.mikeneck.gradle.git.files.IntelliJIDEA
import org.mikeneck.gradle.git.files.MacOSX
import org.mikeneck.gradle.git.files.Windows
import org.mikeneck.gradle.git.files.Java

/**
 * @author mike_neck
 */
class PluginManager {

    PluginContainer container

    List<String> loadIgnoreFiles () {
        List<IgnoreFiles> list = manage()
        return list.collect {
            it.ignoreFiles }.flatten().asList()
    }

    List<IgnoreFiles> manage() {
        List<IgnoreFiles> list = []

        list << Eclipse.git(container)
        list << Gradle.git(container)
        list << IntelliJIDEA.git(container)
        list << Java.git(container)
        list << MacOSX.git(container)
        list << Windows.git(container)

        return list
    }
}
