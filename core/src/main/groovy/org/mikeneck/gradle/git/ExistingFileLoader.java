package org.mikeneck.gradle.git;

import java.util.List;

/**
 * @author mike
 */
public interface ExistingFileLoader {

    public void setFile (GitIgnoreFile file);

    public GitIgnoreFile getFile ();

    public List<String> readContents ();
}
