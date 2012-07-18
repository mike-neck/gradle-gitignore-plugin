package org.mikeneck.gradle.git

/**
 * @author mike_neck
 */
class GitIgnoreFile {

    static String FILE_NAME = '.gitignore'

    File projectHome

    public String getFileName () {
        return "${projectHome.path}/${FILE_NAME}"
    }
}
