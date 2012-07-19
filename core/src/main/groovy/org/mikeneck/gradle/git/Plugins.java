package org.mikeneck.gradle.git;

import org.gradle.api.plugins.PluginContainer;

import java.util.List;

/**
 * @author mike
 */
public interface Plugins {

    public List<String> loadIgnoreFiles ();

    public void setContainer (PluginContainer container);
}
