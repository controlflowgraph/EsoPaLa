package epl.eval.values;

import epl.eval.operations.conjunction.And;
import epl.eval.operations.conjunction.Not;
import epl.eval.operations.conjunction.Or;

public record BooleanValue(boolean value) implements Value, And, Not, Or
{
    @Override
    public String toString()
    {
        return Boolean.toString(this.value);
    }

    @Override
    public Value and(Value value)
    {
        if(value instanceof BooleanValue b)
        {
            return new BooleanValue(this.value && b.value);
        }
        return unsupported(value);
    }

    @Override
    public Value not()
    {
        return new BooleanValue(!this.value);
    }

    @Override
    public Value or(Value value)
    {
        if(value instanceof BooleanValue b)
        {
            return new BooleanValue(this.value || b.value);
        }
        return unsupported(value);
    }

    private Value unsupported(Value value)
    {
        throw new RuntimeException("Unsupported operation of types " + this.getClass().getName() + " and " + value.getClass().getName());
    }
}
