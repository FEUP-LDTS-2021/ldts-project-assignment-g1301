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
}
