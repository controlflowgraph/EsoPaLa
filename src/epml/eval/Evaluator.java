package epml.eval;

import epml.eval.values.Value;

public interface Evaluator
{
    Value evaluate();

    static<T extends Value> T cast(Value value, Class<T> c)
    {
        return c.cast(value);
    }
}
