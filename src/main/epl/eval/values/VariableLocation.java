package epl.eval.values;

import epl.eval.Environment;
import epl.eval.operations.util.Location;

public class VariableLocation implements Value, Location
{
    private final Environment env;
    private final String variable;

    public VariableLocation(Environment env, String variable)
    {
        this.env = env;
        this.variable = variable;
    }

    @Override
    public Value get()
    {
        return this.env.get(this.variable);
    }

    @Override
    public void set(Value value)
    {
        this.env.set(this.variable, value);
    }

    public String getVariable()
    {
        return this.variable;
    }
}
