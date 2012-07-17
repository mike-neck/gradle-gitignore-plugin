package org.mikeneck.gradle.git;

import java.util.List;

/**
 * The class which implements this interface manages ignore files.
 * @author mike_neck
 */
public interface IgnoreFiles {

    /**
     * returns ignore files as {@code java.util.List&lt;String&gt;}
     * @return - file list
     */
    public List<String> getIgnoreFiles ();
}
