package epl.eval.values;

import java.util.function.Function;

public interface Value
{
    default<T> Value op(Class<T> operation, Function<T, Value> compute)
    {
        if(operation.isInstance(this))
        {
            return compute.apply(operation.cast(this));
        }
        throw new RuntimeException("Unsupported operation " + operation.getName() + "!");
    }
}
