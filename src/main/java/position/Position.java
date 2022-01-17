package position;

import position.PositionDefiner;

public class Position implements PositionDefiner {
    private Integer x,y;
    public Position(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }
}
