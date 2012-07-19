package org.mikeneck.gradle.git;

import java.util.List;

/**
 * @author mike
 */
public interface Merger {

    public GitIgnoreWriter merge(ForceIgnore forceIgnore);
}
