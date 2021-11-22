package epl.eval.evaluators;

import epl.eval.operations.util.Location;
import epl.eval.values.Value;

public interface Evaluator
{
    Value evaluate();

    default <T> T val(Class<T> c)
    {
        Value value = evaluate();
        return c.cast(value instanceof Location l ? l.get() : value);
    }

    default Value val()
    {
        Value value = evaluate();
        return value instanceof Location l ? l.get() : value;
    }

    default <T> T as(Class<T> c)
    {
        return c.cast(evaluate());
    }
}
