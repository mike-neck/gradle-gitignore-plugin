package org.mikeneck.gradle.git;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author mike_neck
 */
public class GitIgnoreFileTest {

    @Test
    public void testName () {
        GitIgnoreFile ignoreFile = new GitIgnoreFile();
        ignoreFile.setProjectHome(new File("/Users/gradle/gradle-gitignore-plugin"));

        assertThat(ignoreFile.getFileName(),
                is("/Users/gradle/gradle-gitignore-plugin/.gitignore"));
    }
}
