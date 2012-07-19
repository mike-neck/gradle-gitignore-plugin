package org.mikeneck.gradle.git

/**
 * @author mike_neck
 */
class GitIgnoreReader implements ExistingFileLoader {

    GitIgnoreFile file

    Map<Integer, String> load() {
        def contents = [:]
        if (new File(file.fileName).exists()) {
            new File(file.fileName).eachLine {String item, int line ->
                contents[line] = item
            }
        }
        return contents
    }

    @Override
    public List<String> readContents () {
        return load().collect {it.value}
    }
}
