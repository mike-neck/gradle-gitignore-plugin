package org.mikeneck.gradle.git;

/**
 * @author mike
 */
public interface Merger {

    public GitIgnoreWriter merge(ForceIgnore forceIgnore);
}
