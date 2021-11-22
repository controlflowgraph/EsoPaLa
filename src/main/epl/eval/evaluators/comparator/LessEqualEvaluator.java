package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.LessEqual;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is less or equal to $b"})
public class LessEqualEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public LessEqualEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(LessEqual.class, a -> a.leq(this.b.val()));
    }
}
