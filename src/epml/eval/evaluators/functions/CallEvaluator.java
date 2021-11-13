package epml.eval.evaluators.functions;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.evaluators.util.MultiEvaluator;
import epml.eval.values.FunctionValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "call $func with $args")
public class CallEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator function;
    private final Evaluator arguments;

    public CallEvaluator(Environment env, Evaluator function, Evaluator arguments)
    {
        this.env = env;
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public Value evaluate()
    {
        FunctionValue val = this.function.evaluate().val(FunctionValue.class);
        List<Value> arguments = new ArrayList<>();
        if(this.arguments instanceof MultiEvaluator m)
        {
            for(Evaluator eval : m.getEvaluators())
            {
                arguments.add(eval.evaluate().val());
            }
        }
        else
        {
            arguments.add(this.arguments.evaluate().val());
        }

        return val.call(this.env, arguments);
    }
}
