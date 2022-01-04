import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import spock.lang.Specification

class SpellTemplateTest extends Specification{
    private Position mockPos
    private Position realPos

    def "setup"(){
        mockPos = Mock(Position)
        realPos = new Position(1,2)

    }

    def "SpellHealth Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellHealth(mockPos)
        then:
            assert s.getSymbol()=='H'
    }

    def "SpellGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellGunDamage(mockPos)
        then:
            assert s.getSymbol()=='D'
    }

    def "SpellHealthDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellHealthDamage(mockPos)
        then:
            assert s.getSymbol()=='X'
    }

    def "SpellLessGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellLessGunDamage(mockPos)
        then:
            assert s.getSymbol()=='L'
    }

    def "SpellTPBack Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellTPBack(mockPos)
        then:
            assert s.getSymbol()=='T'
    }

    def "SpellInvincible Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellInvincible(mockPos)
        then:
            assert s.getSymbol()=='I'
    }


    def "SpellNerfed Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellNerfed(mockPos)
        then:
            assert s.getSymbol()=='N'
    }

    def "SpellHealth Draw Test"(){
        given:
            Screen screen
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellHealth(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('H' as char)
    }

    def "SpellGunDamage Draw Test"(){
        given:
            Screen screen
            TerminalSize terminalSize = new TerminalSize(150, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellGunDamage(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == 'D'
    }

    def "SpellHealthDamage Draw Test"(){
        given:
            Screen screen;
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()           // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellHealthDamage(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('X' as char)
    }

    def "SpellLessGunDamage Draw Test"(){
        given:
            Screen screen
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellLessGunDamage(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('L' as char)
    }

    def "SpellTPBack Draw Test"(){
        given:
            Screen screen
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellTPBack(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('T' as char)
    }

    def "SpellInvincible Draw Test"(){
        given:
            Screen screen;
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellInvincible(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('I' as char)
    }


    def "SpellNerfed Draw Test"(){
        given:
            Screen screen
            TerminalSize terminalSize = new TerminalSize(150, 50)
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
            Terminal terminal = terminalFactory.createTerminal()
            screen = new TerminalScreen(terminal)
            screen.setCursorPosition(null)   // we don't need a cursor
            screen.startScreen()             // screens must be started
            screen.doResizeIfNecessary()
            def graphics = screen.newTextGraphics()
            SpellTemplate s
        when:
            s = new SpellNerfed(realPos)
            s.draw(graphics)
        then:
            assert graphics.getCharacter(realPos.x, realPos.y).getCharacter() == ('N' as char)
    }
}
