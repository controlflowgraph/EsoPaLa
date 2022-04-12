package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.GreaterEqual;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is greater or equal to $b"})
public class GreaterEqualEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public GreaterEqualEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(GreaterEqual.class, a -> a.geq(this.b.val()));
    }
}
