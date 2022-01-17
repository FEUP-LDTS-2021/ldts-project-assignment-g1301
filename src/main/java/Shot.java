import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class Shot implements ShotDefiner {
    private Integer damage;
    private Integer width;
    private Integer velocity;
    private Position pos;


    public Shot(Integer damage,Integer width,Integer velocity,Position pos){
        this.damage = damage;
        this.width = width;
        this.velocity = velocity;
        this.pos = pos;
    }


    @Override
    public Integer getDamage() {
        return damage;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getVelocity() {
        return velocity;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void moveDown() {
        pos.setY(pos.getY()+1);
    }

    @Override
    public void moveUp() {
        pos.setY(pos.getY() - 1);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {

        graphics.setForegroundColor(TextColor.Factory.fromString(getColor()));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "|");

        for(int i=1;i<=width/2;i++) {
            graphics.setForegroundColor(TextColor.Factory.fromString(getColor()));
            graphics.putString(new TerminalPosition(pos.getX()+i, pos.getY()), "|");
            graphics.putString(new TerminalPosition(pos.getX()-i, pos.getY()), "|");
        }

    }

    @Override
    public String getColor() {
        String color;
        switch (velocity) {
            case 2:
                color = "#FFFF00";
                break;
            case 3:
                color = "#00FFFF";
                break;
            default:
                color = "#FFFFFF";
                break;
        }
        return color;
    }
}
