package spell.template;

import position.Position;
import spell.SpellDefiner;

public abstract class SpellTemplate implements SpellDefiner {
    protected Character symbol;
    protected Position pos;
    protected long time;
    @Override
    public final Position getPosition() {
        return pos;
    }

    @Override
    public final Character getSymbol() {
        return symbol;
    }

    @Override
    public final long getTime(){
        return time;
    }
}
