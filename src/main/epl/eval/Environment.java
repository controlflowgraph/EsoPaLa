package epl.eval;

import epl.eval.values.Value;

import java.util.Stack;

public class Environment
{
    private final Stack<Scope> scopes = new Stack<>();

    public Environment()
    {
        push();
    }

    public void push()
    {
        this.scopes.push(new Scope());
    }

    public void pop()
    {
        if(this.scopes.size() <= 1) throw new RuntimeException("Unable to pop global scope!");
        this.scopes.pop();
    }

    public void set(String variable, Value value)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            if(this.scopes.get(i).contains(variable))
            {
                this.scopes.get(i).set(variable, value);
            }
        }
        this.scopes.get(this.scopes.size() - 1).set(variable, value);
    }

    public void setLocal(String variable, Value value)
    {
        this.scopes.get(this.scopes.size() - 1).set(variable, value);
    }

    public Value get(String variable)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            if(this.scopes.get(i).contains(variable))
            {
                return this.scopes.get(i).get(variable);
            }
        }
        throw new RuntimeException("Unknown variable! (" + variable + ")");
    }
}
