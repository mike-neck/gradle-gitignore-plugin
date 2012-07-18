package org.mikeneck.gradle.git

/**
 * @author mike_neck
 */
class GitIgnoreReader {

    GitIgnoreFile file

    Map<Integer, String> contents

    void load() {
        contents = [:]
        new File(file.fileName).eachLine {String item, int line ->
            contents[line] = item
        }
    }

    List<String> readContents () {
        return contents.collect {it.value}
    }
}
