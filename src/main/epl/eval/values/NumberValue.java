package epl.eval.values;

import epl.eval.operations.arithmetic.*;

public record NumberValue(double number) implements Value, Addition, Subtraction, Multiplication, Division, Modulo
{
    @Override
    public String toString()
    {
        return Double.toString(this.number);
    }

    @Override
    public Value add(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new NumberValue(this.number + n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value sub(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new NumberValue(this.number - n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value mul(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new NumberValue(this.number * n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value div(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new NumberValue(this.number / n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value mod(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new NumberValue(this.number % n.number);
        }
        return unsupported(value);
    }

    private Value unsupported(Value value)
    {
        throw new RuntimeException("Unsupported operation of types " + this.getClass().getName() + " and " + value.getClass().getName());
    }
}
