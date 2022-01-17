import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import position.Position
import spell.template.SpellGunDamage
import spell.template.SpellHealth
import spell.template.SpellHealthDamage
import spell.template.SpellInvincible
import spell.template.SpellLessGunDamage
import spell.template.SpellNerfed
import spell.template.SpellTPBack
import spell.template.SpellTemplate
import spock.lang.Specification

class SpellTemplateTest extends Specification{
    private Position mockPos
    private Position realPos

    def "setup"(){
        mockPos = Mock(Position)
        realPos = new Position(1,2)

    }

    def "spell.template.SpellHealth Test"(){
        given:
        SpellTemplate s
        when:
            s = new SpellHealth(mockPos)
        then:
            assert s.getSymbol()=='H'
    }

    def "spell.template.SpellGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellGunDamage(mockPos)
        then:
            assert s.getSymbol()=='D'
    }

    def "spell.template.SpellHealthDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellHealthDamage(mockPos)
        then:
            assert s.getSymbol()=='X'
    }

    def "spell.template.SpellLessGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellLessGunDamage(mockPos)
        then:
            assert s.getSymbol()=='L'
    }

    def "spell.template.SpellTPBack Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellTPBack(mockPos)
        then:
            assert s.getSymbol()=='T'
    }

    def "spell.template.SpellInvincible Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellInvincible(mockPos)
        then:
            assert s.getSymbol()=='I'
    }


    def "spell.template.SpellNerfed Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellNerfed(mockPos)
        then:
            assert s.getSymbol()=='N'
    }

    def "spell.template.SpellHealth Draw Test"(){
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

    def "spell.template.SpellGunDamage Draw Test"(){
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

    def "spell.template.SpellHealthDamage Draw Test"(){
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

    def "spell.template.SpellLessGunDamage Draw Test"(){
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

    def "spell.template.SpellTPBack Draw Test"(){
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

    def "spell.template.SpellInvincible Draw Test"(){
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


    def "spell.template.SpellNerfed Draw Test"(){
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
