package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.Equal;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is equal to $b"})
public class EqualEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public EqualEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Equal.class, a -> a.eq(this.b.val()));
    }
}
