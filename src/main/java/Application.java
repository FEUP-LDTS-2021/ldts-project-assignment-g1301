import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Game g = new Game();
            g.run();
        }
        catch(IOException e){
            e.printStackTrace();;
        }
    }
}