package epml.eval.values;

import epml.eval.Environment;
import epml.eval.Evaluator;

import java.util.List;

public class FunctionValue implements Value
{
    private final List<String> parameters;
    private final Evaluator body;

    public FunctionValue(List<String> parameters, Evaluator body)
    {
        this.parameters = parameters;
        this.body = body;
    }


    public Value call(Environment env, List<Value> arguments)
    {
        env.push();

        for(int i = 0; i < this.parameters.size(); i++)
        {
            env.setLocal(this.parameters.get(i), arguments.get(i));
        }

        Value result = this.body.evaluate();

        env.pop();

        return result;
    }
}
