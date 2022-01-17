import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import spock.lang.Specification

class ShotTest extends Specification{
    private Position pos;
    def "setup"(){
        pos = Mock(Position)
        pos.getX() >> 1
        pos.getY() >> 2
    }
    def "Damage getter test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos);
        then:
            assert s.getDamage() == 1
    }
    def "Radius getter test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos);
        then:
            assert s.getWidth() == 2
    }
    def "Velocity getter Test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos);
        then:
            assert s.getVelocity() == 3
    }
    def "Position getter Test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos)
        then:
            assert s.getPosition().getX()==1
            assert s.getPosition().getY()==2
    }
    def "Move down Test"() {
        given:
            ShotDefiner s;
        when:
            s = new Shot(1, 2, 3, new Position(1, 2))
            Integer oldY = s.getPosition().getY()
            s.moveDown()
        then:
            assert s.getPosition().getY() == oldY + 1
    }


    def "Get color Test White"() {
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,3,1,pos);
        then:
            assert s.getColor() == "#FFFFFF";
    }

    def "Get color Test Yellow"() {
        given:
            ShotDefiner s;
        when:
           s = new Shot(1,3,2,pos);
        then:
            assert s.getColor() == "#FFFF00";
    }

    def "Get color Test Blue"() {
        given:
           ShotDefiner s;
        when:
           s = new Shot(1,3,3,pos);
        then:
           assert s.getColor() == "#00FFFF";
    }


    def "Normal shot draw"(){
        given:
            Screen screen;
            TerminalSize terminalSize = new TerminalSize(150, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();
            def graphics = screen.newTextGraphics();
            ShotDefiner s
        when:
            s = new Shot(0,0,0,pos);
            s.draw(graphics);
        then:
            assert graphics.getCharacter(pos.x, pos.y).getCharacter()== ('|' as char);
    }
    def "Big shot draw"(){
        given:
            Screen screen;
            TerminalSize terminalSize = new TerminalSize(150, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();
            def graphics = screen.newTextGraphics();
            ShotDefiner s
        when:
            s = new Shot(0,3,0,pos);
            s.draw(graphics);
        then:
            for(Integer x=pos.x-1; x<=pos.x+1; x++)
                assert graphics.getCharacter(x, pos.y).getCharacter()== ('|' as char);
    }
}
