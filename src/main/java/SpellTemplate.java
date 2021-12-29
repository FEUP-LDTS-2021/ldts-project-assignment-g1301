abstract class SpellTemplate implements SpellDefiner {
    protected Character symbol;
    protected Position pos;
    @Override
    public final Position getPosition() {
        return pos;
    }

    @Override
    public final Character getSymbol() {
        return symbol;
    }
}
