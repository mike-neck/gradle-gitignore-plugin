package org.mikeneck.gradle.git;

import groovy.lang.Closure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public List<String> getFiles() {
        return Collections.unmodifiableList(files);
    }
}
