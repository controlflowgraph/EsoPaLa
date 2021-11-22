package epl.eval.evaluators.arithmetic;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.arithmetic.Addition;
import epl.eval.operations.arithmetic.Division;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a divided by $b" })
public class DivisionEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public DivisionEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Division.class, a -> a.div(this.b.val()));
    }
}
