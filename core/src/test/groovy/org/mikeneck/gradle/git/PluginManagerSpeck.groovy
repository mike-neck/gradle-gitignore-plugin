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
        setup: 'prepare properties'
            useWindows()
            def container = createMock(PluginContainer)
            expect(container.hasPlugin('eclipse')).andReturn(true)
            expect(container.hasPlugin('idea')).andReturn(true)
            expect(container.hasPlugin('java')).andReturn(true)
            replay(container)

        when: "all plugin are used"
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
        setup: 'prepare properties'
        useWindows()
        def container = createMock(PluginContainer)
        expect(container.hasPlugin('eclipse')).andReturn(false)
        expect(container.hasPlugin('idea')).andReturn(true)
        expect(container.hasPlugin('java')).andReturn(true)
        replay(container)

        when: "all plugin are used"
        def manager = new PluginManager(container: container)
        def ignoreFiles = manager.manage()

        then: "count plugins"
        ignoreFiles.size() == 6
        ignoreFiles[0].ignoreFiles.size() ==  0
        ignoreFiles[1].ignoreFiles.size() ==  2
        ignoreFiles[2].ignoreFiles.size() ==  5
        ignoreFiles[3].ignoreFiles.size() ==  1
        ignoreFiles[4].ignoreFiles.size() ==  0
        ignoreFiles[5].ignoreFiles.size() ==  3

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
