package epl.eval.evaluators.function;

import epl.eval.Environment;
import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.FunctionValue;
import epl.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(patterns = { "call $f with $a (* , $a)", "call $f" })
public class FunctionCallEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator function;
    private final List<Evaluator> arguments;

    public FunctionCallEvaluator(Environment env, Evaluator function)
    {
        this.env = env;
        this.function = function;
        this.arguments = List.of();
    }

    public FunctionCallEvaluator(Environment env, Evaluator function, List<Evaluator> arguments)
    {
        this.env = env;
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public Value evaluate()
    {
        FunctionValue val = this.function.val(FunctionValue.class);
        List<Value> values = new ArrayList<>();
        for(Evaluator evaluator : this.arguments)
        {
            values.add(evaluator.evaluate());
        }
        return val.call(this.env, values);
    }
}
