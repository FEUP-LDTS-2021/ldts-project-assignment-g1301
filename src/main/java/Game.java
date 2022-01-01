import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game implements GameDefiner{
    private Screen screen;
    private final Arena arena;

    public Game(){
        arena = new Arena(150,50);
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw() throws IOException {
        this.screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        this.screen.refresh();
    }

    @Override
    public void run() throws IOException {
        while (true){
            if(arena.getEnemies().size()==0) arena.createEnemies();
            draw();
            if(arena.getSpaceship().isDead()){
                screen.close();
                System.out.println("Game Over");
                break;
            }
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.EOF)
                break;
            if(key.getKeyType()== KeyType.Character &&  key.getCharacter()=='q') {
                screen.close();
            }
            arena.moveShots();
            arena.processKey(key);
            arena.moveEnemies();
            arena.shootEnemies();
            arena.checkShotCollisions();
            arena.checkShotsHitEnemies();
            arena.checkShotsHitSpaceship();
        }
    }
}
