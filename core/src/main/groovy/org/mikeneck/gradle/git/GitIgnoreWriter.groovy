package org.mikeneck.gradle.git

/**
 * @author mike_neck
 */
class GitIgnoreWriter {

    GitIgnoreFile gitIgnoreFile

    List<String> fileList

    GitIgnoreWriter (GitIgnoreFile gitIgnoreFile, List<String> fileList) {
        this.gitIgnoreFile = gitIgnoreFile
        this.fileList = fileList
    }
}
