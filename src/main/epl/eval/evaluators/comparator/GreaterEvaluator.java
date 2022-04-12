package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.Greater;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is greater than $b" })
public class GreaterEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public GreaterEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Greater.class, a -> a.gre(this.b.val()));
    }
}
