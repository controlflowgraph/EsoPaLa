package epml.eval.evaluators.functions;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.Value;

@LanguagePattern(pattern = "return $val")
public class ReturnEvaluator implements Evaluator
{
    private final Evaluator val;

    public ReturnEvaluator(Environment env, Evaluator val)
    {
        this.val = val;
    }

    @Override
    public Value evaluate()
    {
        return this.val.evaluate().val();
    }
}
