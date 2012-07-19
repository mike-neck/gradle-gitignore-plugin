package org.mikeneck.gradle.git;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author mike
 */
public class GitIgnoreMergerTest {

    @Test
    public void firstStage () {
        def merge = GitIgnoreMerger
                .init(new MockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce())

        assert merge.size() == 8
    }

    @Test
    void firstStageIsEmpty () {
        def merge = GitIgnoreMerger
                .init(new EmptyMockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce())

        assert merge.size() == 6
    }

    @Test
    void thirdStageEndsWithBlank () {
        def merge = GitIgnoreMerger
                .init(new EmptyMockReader())
                .plugins(new MockPlugins())
                .merge(new MockForce2())

        assert merge.size() == 5
    }

    class MockReader implements ExistingFileLoader {
        @Override
        void setFile(GitIgnoreFile file) {
        }

        @Override
        List<String> readContents() {
            return ["*~", "_*", ".test"]
        }
    }

    class EmptyMockReader implements ExistingFileLoader {
        @Override
        void setFile(GitIgnoreFile file) {
        }

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
