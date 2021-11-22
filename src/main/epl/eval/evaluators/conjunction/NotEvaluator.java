package epl.eval.evaluators.conjunction;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.conjunction.Not;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "not $a" })
public class NotEvaluator implements Evaluator
{
    private final Evaluator a;

    public NotEvaluator(Evaluator a)
    {
        this.a = a;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Not.class, Not::not);
    }
}
