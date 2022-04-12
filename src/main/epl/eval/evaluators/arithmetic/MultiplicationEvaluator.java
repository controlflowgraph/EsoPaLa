package epl.eval.evaluators.arithmetic;

import epl.eval.*;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.arithmetic.Multiplication;
import epl.eval.values.NumberValue;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a multiplied $b", "$a times $b" })
public class MultiplicationEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public MultiplicationEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Multiplication.class, a -> a.mul(this.b.val()));
    }
}
