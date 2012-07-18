package org.mikeneck.gradle.git;

import groovy.lang.Closure;

import java.util.ArrayList;
import java.util.Arrays;
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
public class GitIgnore {

    public static final String extension = "gitignore";

    public List<String> files = new ArrayList<String>();

    public void files (String... items) {
        files = Arrays.asList(items);
    }

    public void files (List<String> items) {
        files = items;
    }

    public void files (Closure<List<String>> closure) {
        files = closure.call();
    }
}
