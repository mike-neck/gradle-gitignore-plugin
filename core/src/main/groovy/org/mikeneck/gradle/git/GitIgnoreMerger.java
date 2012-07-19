package org.mikeneck.gradle.git;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mike
 */
abstract public class GitIgnoreMerger implements Merger {

    protected GitIgnoreFile gitIgnoreFile;

    public static SecondMerger init(ExistingFileLoader existingFileLoader) {
        List<String> initialFiles = existingFileLoader.readContents();
        List<String> list = new ArrayList<String>(initialFiles);
        if (list.size() > 0) {
            list.add("");
        }
        return new SecondMerger(list, existingFileLoader);
    }

    private static class SecondMerger {

        private final List<String> files;

        private ExistingFileLoader existingFileLoader;

        public SecondMerger(final List<String> initialFiles, ExistingFileLoader existingFileLoader) {
            files = Collections.unmodifiableList(initialFiles);
            this.existingFileLoader = existingFileLoader;
        }

        public ThirdMerger plugins(Plugins plugins) {
            List<String> list = new ArrayList<String>(files.size() + 20);
            list.addAll(files);
            for (String file : plugins.loadIgnoreFiles()) {
                if (files.contains(file) == false) {
                    list.add(file);
                }
            }
            list.add("");
            return new ThirdMerger(list, existingFileLoader);
        }
    }

    private static class ThirdMerger extends GitIgnoreMerger {

        private final List<String> files;

        public ThirdMerger(List<String> list, ExistingFileLoader existingFileLoader) {
            files = Collections.unmodifiableList(list);
            gitIgnoreFile = existingFileLoader.getFile();
        }

        @Override
        public GitIgnoreWriter merge (ForceIgnore forceIgnore) {
            List<String> list = new ArrayList<String>((int) (files.size() * 1.6));
            list.addAll(files);
            for (String file : forceIgnore.getFiles()) {
                if (files.contains(file) == false) {
                    list.add(file);
                }
            }
            list.add("");
            return new GitIgnoreWriter(gitIgnoreFile, list);
        }
    }
}
