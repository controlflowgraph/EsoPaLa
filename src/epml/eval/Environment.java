package epml.eval;

import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment
{
    private final List<Scope> scopes = new ArrayList<>();

    public Environment()
    {
        this.scopes.add(new Scope());
    }

    public Value get(String variable)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            Scope scope = this.scopes.get(i);
            if(scope.contains(variable))
            {
                return scope.get(variable);
            }
        }
        throw new RuntimeException("Unknown variable! (" + variable + ")");
    }

    public void set(String variable, Value value)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            Scope scope = this.scopes.get(i);
            if(scope.contains(variable))
            {
                scope.set(variable, value);
                return;
            }
        }
        this.scopes.get(0).set(variable, value);
    }

    public void setLocal(String variable, Value value)
    {
        this.scopes.get(this.scopes.size() - 1).set(variable, value);
    }

    public void push()
    {
        this.scopes.add(new Scope());
    }

    public void pop()
    {
        this.scopes.remove(this.scopes.size() -  1);
    }
}

class Scope
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
