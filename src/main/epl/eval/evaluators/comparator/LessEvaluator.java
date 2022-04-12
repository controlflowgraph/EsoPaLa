package epl.eval.evaluators.comparator;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.comparators.Less;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a is less to $b"})
public class LessEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public LessEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Less.class, a -> a.les(this.b.val()));
    }
}
