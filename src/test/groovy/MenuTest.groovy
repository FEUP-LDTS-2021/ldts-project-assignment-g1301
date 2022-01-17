import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration
import spock.lang.Specification

import java.awt.Font

class MenuTest extends Specification{
    def "menu_draw"() {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.NOTHING, new Font(Font.MONOSPACED, Font.PLAIN, 40));
        Terminal terminal = new DefaultTerminalFactory()
                .setForceAWTOverSwing(true)
                .setInitialTerminalSize(new TerminalSize(50,15))
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        MenuDefiner m;
        def graphics = screen.newTextGraphics();
        when:
        m = new Menu();
        m.draw(graphics);
        then:
        assert graphics.getCharacter(24, 6).getCharacter() == ('P' as char);
        assert graphics.getCharacter(19, 2).getCharacter() == ('S' as char);
        assert graphics.getCharacter(24, 9).getCharacter() == ('Q' as char);
    }
}
