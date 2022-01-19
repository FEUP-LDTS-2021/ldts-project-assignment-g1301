package com.shot;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;

import java.io.IOException;


public class Shot implements ShotDefiner {
    private Integer damage;
    private Integer width;
    private Position pos;


    public Shot(Integer damage,Integer width,Position pos){
        this.damage = damage;
        this.width = width;
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

        graphics.setForegroundColor(TextColor.Factory.fromString("#FEFE33"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "l");

        for(int i=1;i<=width/2;i++) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FEFE33"));
            graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "|");
            graphics.putString(new TerminalPosition(pos.getX()+i, pos.getY()), "|");
            graphics.putString(new TerminalPosition(pos.getX()-i, pos.getY()), "|");
        }

    }

}
