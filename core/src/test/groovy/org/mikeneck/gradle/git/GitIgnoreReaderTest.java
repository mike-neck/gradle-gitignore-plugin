package org.mikeneck.gradle.git;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class GitIgnoreReaderTest {

    private static URL TEST_PROJECT = GitIgnoreReaderTest.class.getClassLoader().getResource("test/");

    private GitIgnoreFile ignoreFile;
    private GitIgnoreReader reader;

    @Before
    public void setup () {
        ignoreFile = new GitIgnoreFile();
        ignoreFile.setProjectHome(new File(TEST_PROJECT.getFile()));
        reader = new GitIgnoreReader();
        reader.setFile(ignoreFile);
        reader.load();
    }

    @Test
    public void readContentsProperly () {
        Map<Integer,String> contents = reader.getContents();

        assertThat(contents.size(), is(8));
    }

    @Test
    public void convertContentsProperly () {
        List<String> contents = reader.readContents();

        assertThat("*.ipr", isIn(contents));
        assertThat("*.iml", isIn(contents));
        assertThat("*.iws", isIn(contents));
        assertThat("*.class", isIn(contents));
        assertThat("test/", isIn(contents));
        assertThat("!.gitignore", isIn(contents));
    }
}
