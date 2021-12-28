public interface PositionDefiner {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    @Override
    public boolean equals(Object o);
}
