package epl.eval.evaluators.function;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "return $a" })
public class ReturnEvaluator implements Evaluator
{
    private final Evaluator source;

    public ReturnEvaluator(Evaluator source)
    {
        this.source = source;
    }

    @Override
    public Value evaluate()
    {
        return this.source.val();
    }
}
