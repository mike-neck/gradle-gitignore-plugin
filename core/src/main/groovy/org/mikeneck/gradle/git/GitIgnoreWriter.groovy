package org.mikeneck.gradle.git

/**
 * @author mike_neck
 */
class GitIgnoreWriter {

    String fileName

    List<String> fileList

    GitIgnoreWriter (GitIgnoreFile gitIgnoreFile, List<String> fileList) {
        this.fileName = gitIgnoreFile.fileName
        this.fileList = fileList
    }

    Closure<Void> getClosure () {
        return {
            def file = new File(fileName)
            file.write(fileList.join('\n'), 'UTF-8')
        }
    }
}
