package org.mikeneck.gradle.git;

import groovy.lang.Closure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * extension for additional ignoring files<br/>
 * <h3>Usage</h3>
 * <br/>
 * <h5>example (Android)</h5>
 * <pre><code>
 *     gitignore {
 *         files << ['*.apk', '*.ap_', '*.dex', 'gen/']
 *     }
 * </code></pre>
 * @author mike
 */
public class GitIgnore implements ForceIgnore {

    public static final String extension = "gitignore";

    public List<String> ignoreFiles = new ArrayList<String>();

    public void files (String... items) {
        ignoreFiles = Arrays.asList(items);
    }

    public void files (List<String> items) {
        ignoreFiles = items;
    }

    public void files (Closure<List<String>> closure) {
        ignoreFiles = closure.call();
    }

    @Override
    public List<String> getFiles() {
        return Collections.unmodifiableList(ignoreFiles);
    }
}
