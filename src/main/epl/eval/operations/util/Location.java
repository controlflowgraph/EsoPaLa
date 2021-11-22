package epl.eval.operations.util;

import epl.eval.values.Value;

public interface Location
{
    Value get();

    void set(Value value);
}
