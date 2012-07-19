package org.mikeneck.gradle.git;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author mike
 */
@RunWith(Enclosed.class)
public class GitIgnoreReaderTest {

    public static class FileExistingCase {
        private ExistingFileLoader reader;

        private static String SRC_TEST_RECOURCES_TEST = "src/test/resources/test";

        private static String CORE_SRC_TEST_RECOUREC_TEST = "core/src/test/resources/test";

        @Before
        public void setup () {
            String file;
            if (new File(CORE_SRC_TEST_RECOUREC_TEST).exists()) {
                file = CORE_SRC_TEST_RECOUREC_TEST;
            } else {
                file = SRC_TEST_RECOURCES_TEST;
            }

            GitIgnoreFile ignoreFile = new GitIgnoreFile();
            ignoreFile.setProjectHome(new File(file));

            reader = new GitIgnoreReader();
            reader.setFile(ignoreFile);
        }

        @Test
        public void readContentsProperly () {
            List<String> contents = reader.readContents();

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

        @Test
        public void anOrderIsSaved () {
            List<String> list = reader.readContents();
            int iterator = 0;

            for (int size = list.size();
                 iterator < size && "test/".equals(list.get(iterator)) == false;) {
                iterator += 1;
            }

            if (iterator == list.size()) {
                fail();
            } else {
                assertThat(list.get(iterator + 1), is("!.gitignore"));
            }
        }
    }

    public static class FileNotExistingCase {

        @Test
        public void ifFileIsNotExistReturnsEmptyList () {
            GitIgnoreFile ignoreFile = new GitIgnoreFile();
            ignoreFile.setProjectHome(new File("testWhichDoesNotExist"));

            GitIgnoreReader reader = new GitIgnoreReader();
            reader.setFile(ignoreFile);

            assertThat(reader.readContents().size(), is(0));
        }
    }
}
