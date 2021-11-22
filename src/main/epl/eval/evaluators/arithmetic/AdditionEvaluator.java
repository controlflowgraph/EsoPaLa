package epl.eval.evaluators.arithmetic;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.arithmetic.Addition;
import epl.eval.values.NumberValue;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a plus $b" })
public class AdditionEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public AdditionEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Addition.class, a -> a.add(this.b.val()));
    }
}
