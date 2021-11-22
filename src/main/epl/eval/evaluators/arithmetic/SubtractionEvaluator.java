package epl.eval.evaluators.arithmetic;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.arithmetic.Addition;
import epl.eval.operations.arithmetic.Subtraction;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a minus $b" })
public class SubtractionEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public SubtractionEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Subtraction.class, a -> a.sub(this.b.val()));
    }
}
