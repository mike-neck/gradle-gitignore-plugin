package org.mikeneck.gradle.git;

import org.junit.Test;

import java.util.Arrays;
import java.util.List
import org.gradle.api.plugins.PluginContainer;

/**
 * @author mike
 */
public class GitIgnoreMergerTest {

    @Test
    public void firstStage () {
        def writer = GitIgnoreMerger
                .init(new MockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce())

        assert writer.fileList.size() == 8
    }

    @Test
    void firstStageIsEmpty () {
        def writer = GitIgnoreMerger
                .init(new EmptyMockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce())

        assert writer.fileList.size() == 6
    }

    @Test
    void thirdStageEndsWithBlank () {
        def writer = GitIgnoreMerger
                .init(new EmptyMockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce2())

        assert writer.fileList.size() == 5
    }

    class MockReader implements ExistingFileLoader {
        @Override void setFile(GitIgnoreFile file) {}
        @Override GitIgnoreFile getFile () {return new GitIgnoreFile(projectHome: new File('.'))}
        @Override
        List<String> readContents() {
            return ["*~", "_*", ".test"]
        }
    }

    class EmptyMockReader implements ExistingFileLoader {
        @Override void setFile(GitIgnoreFile file) {}
        @Override GitIgnoreFile getFile () {return new GitIgnoreFile(projectHome: new File('.'))}
        @Override
        List<String> readContents() {
            return []
        }
    }

    class MockPlugins implements Plugins {
        @Override
        public List<String> loadIgnoreFiles() {
            return [".test", ".log"]
        }
        @Override void setContainer(PluginContainer container) {}
    }

    class MockForce implements ForceIgnore {
        @Override
        List<String> getFiles() {
            return ["*~", ".log", ".class"]
        }
    }

    class MockForce2 implements ForceIgnore {
        @Override
        List<String> getFiles () {
            return [".bak", ".log"]
        }
    }
}
