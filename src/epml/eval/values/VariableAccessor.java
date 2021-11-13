package epml.eval.values;

import epml.eval.Environment;

public class VariableAccessor implements Accessor
{
    private final Environment env;
    private final String variable;

    public VariableAccessor(Environment env, String variable)
    {
        this.env = env;
        this.variable = variable;
    }

    public void set(Value value)
    {
        this.env.set(this.variable, value);
    }

    public void setLocal(Value value)
    {
        this.env.setLocal(this.variable, value);
    }

    public Value get()
    {
        return this.env.get(this.variable);
    }

    public String getVariable()
    {
        return this.variable;
    }
}
