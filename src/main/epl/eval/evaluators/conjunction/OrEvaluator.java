package epl.eval.evaluators.conjunction;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.conjunction.Or;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a or $b" })
public class OrEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public OrEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Or.class, a -> a.or(this.b.val()));
    }
}
