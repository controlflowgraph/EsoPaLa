package epl.eval.values;

import epl.eval.operations.arithmetic.*;
import epl.eval.operations.comparators.*;

public record NumberValue(double number) implements Value, Addition, Subtraction, Multiplication, Division, Modulo, Greater, GreaterEqual, Less, LessEqual, Equal, Unequal
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

    @Override
    public Value gre(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number > n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value geq(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number >= n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value les(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number < n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value leq(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number <= n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value eq(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number == n.number);
        }
        return unsupported(value);
    }

    @Override
    public Value ueq(Value value)
    {
        if(value instanceof NumberValue n)
        {
            return new BooleanValue(this.number == n.number);
        }
        return unsupported(value);
    }

    private Value unsupported(Value value)
    {
        throw new RuntimeException("Unsupported operation of types " + this.getClass().getName() + " and " + value.getClass().getName());
    }
}
