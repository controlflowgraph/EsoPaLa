package epl.eval;

import epl.eval.values.Value;

import java.util.HashMap;
import java.util.Map;

public class Scope
{
    private final Map<String, Value> variables = new HashMap<>();

    public boolean contains(String variable)
    {
        return this.variables.containsKey(variable);
    }

    public Value get(String variable)
    {
        return this.variables.get(variable);
    }

    public void set(String variable, Value value)
    {
        this.variables.put(variable, value);
    }
}
