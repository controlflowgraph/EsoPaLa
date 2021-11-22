package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.Unequal;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is not equal to $b"})
public class UnequalEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public UnequalEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Unequal.class, a -> a.ueq(this.b.val()));
    }
}
