package epml.eval.evaluators.functions;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.Value;

@LanguagePattern(pattern = "the result of $call")
public class ResultOfEvaluator implements Evaluator
{
    private final Evaluator call;

    public ResultOfEvaluator(Environment env, Evaluator call)
    {
        this.call = call;
    }

    @Override
    public Value evaluate()
    {
        return this.call.evaluate();
    }
}
