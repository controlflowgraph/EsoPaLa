package epl.eval.values;

import epl.eval.Environment;
import epl.eval.evaluators.Evaluator;

import java.util.List;

public class FunctionValue implements Value
{
    private final List<String> arguments;
    private final Evaluator body;

    public FunctionValue(List<String> arguments, Evaluator body)
    {
        this.arguments = arguments;
        this.body = body;
    }

    public Value call(Environment env, List<Value> values)
    {
        if(values.size() != this.arguments.size()) throw new RuntimeException("Expected " + this.arguments.size() + " arguments but got " + values.size() + "!");
        env.push();
        for(int i = 0; i < this.arguments.size(); i++)
        {
            env.setLocal(this.arguments.get(i), values.get(i));
        }
        Value val = this.body.val();
        env.pop();
        return val;
    }
}
