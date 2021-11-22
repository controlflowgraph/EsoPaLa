package epl.eval.evaluators.conjunction;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.conjunction.And;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a and $b" })
public class AndEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public AndEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public Value evaluate()
    {
        return this.a.val().op(And.class, a -> a.and(this.b.val()));
    }
}
