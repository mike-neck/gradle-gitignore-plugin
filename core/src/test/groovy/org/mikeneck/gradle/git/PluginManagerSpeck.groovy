package org.mikeneck.gradle.git

import spock.lang.Specification

import static org.easymock.EasyMock.*;
import org.gradle.api.plugins.PluginContainer



/**
 * @author mike_neck
 */
class PluginManagerSpeck extends Specification {

    private Properties properties

    private Properties testProperties

    static final String OS_NAME = 'os.name'

    static final String MAC_OS_X = 'Mac OS X'

    static final String WINDOWS = 'Windows'

    def 'windows case plugin manager returns plugins 6 and assert its values' () {
        setup: 'windows/ eclipse/ idea/ java'
        useWindows()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(true)
        expect(container.hasPlugin('idea')).andReturn(true)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def ignoreFiles = manager.manage()

        then: "count plugins"
        ignoreFiles.size() == 6
        ignoreFiles[0].ignoreFiles.size() == 18
        ignoreFiles[1].ignoreFiles.size() ==  2
        ignoreFiles[2].ignoreFiles.size() ==  5
        ignoreFiles[3].ignoreFiles.size() ==  1
        ignoreFiles[4].ignoreFiles.size() ==  0
        ignoreFiles[5].ignoreFiles.size() ==  3

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    def 'in mac os x case with idea only' () {
        setup: 'mac/ idea/ java'
        useMac()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(false)
        expect(container.hasPlugin('idea')).andReturn(true)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def ignoreFiles = manager.manage()

        then: "count plugins"
        ignoreFiles.size() == 6
        ignoreFiles[0].ignoreFiles.size() ==  0
        ignoreFiles[1].ignoreFiles.size() ==  2
        ignoreFiles[2].ignoreFiles.size() ==  5
        ignoreFiles[3].ignoreFiles.size() ==  1
        ignoreFiles[4].ignoreFiles.size() ==  4
        ignoreFiles[5].ignoreFiles.size() ==  0

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    def 'windows with full plugin case counts ignore files' () {
        setup: 'windows/ eclipse/ idea/ java'
        useWindows()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(true)
        expect(container.hasPlugin('idea')).andReturn(true)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def files = manager.loadIgnoreFiles()

        then: "count ignore files"
        files.size() == 29

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    def 'mac os x with eclipse plugin case counts ignore files' () {
        setup: 'mac/ eclipse/ java'
        useMac()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(true)
        expect(container.hasPlugin('idea')).andReturn(false)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def files = manager.loadIgnoreFiles()

        then: "count ignore files"
        files.size() == 25

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    def 'mac os x with all plugin case files detail' () {
        setup: 'mac/ eclipse/ idea/ java'
        useMac()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(true)
        expect(container.hasPlugin('idea')).andReturn(true)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def files = manager.loadIgnoreFiles()

        then: "assertion files in detail"
        '.DS_Store' in files
        '.classpath' in files
        'out/' in files
        '*.class' in files
        'build/' in files
        !('Thumbs.db' in files)

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    def 'windows with eclipse plugin case files detail' () {
        setup: 'windows/ idea/ java'
        useWindows()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(true)
        expect(container.hasPlugin('idea')).andReturn(false)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "operation"
        def manager = new PluginManager(container: container)
        def files = manager.loadIgnoreFiles()

        then: "assertion files in detail"
        !('.DS_Store' in files)
        'Thumbs.db' in files
        '.classpath' in files
        !('out/' in files)
        '*.class' in files
        'build/' in files

        cleanup: 'recovery System.properties'
        recoveryProperties()
    }

    void useMac () {
        prepareProperties()
        testProperties[OS_NAME] = MAC_OS_X
    }

    void useWindows () {
        prepareProperties()
        testProperties[OS_NAME] = WINDOWS
    }

    void prepareProperties () {
        properties = System.getProperties()
        testProperties = new Properties()
        properties.each {
            if (OS_NAME != it.key) {
                testProperties[it.key] = it.value
            }
        }
        System.setProperties(testProperties)
    }

    void recoveryProperties () {
        System.setProperties(properties)
    }
}
