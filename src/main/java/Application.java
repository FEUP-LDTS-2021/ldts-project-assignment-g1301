import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Game g = Game.getInstance();
            g.run();
            Menu m = new Menu();
            m.interactions();
        }
        catch(IOException e){
            e.printStackTrace();;
        }
    }
}