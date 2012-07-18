package org.mikeneck.gradle.git;

import java.util.List;

/**
 * @author mike
 */
public interface Merger {

    public List<String> merge(ForceIgnore forceIgnore);
}
