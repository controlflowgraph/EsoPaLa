package epml.eval.evaluators.bool;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "$a and $b")
public class AndEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public AndEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        BooleanValue a = this.a.evaluate().val(BooleanValue.class);
        BooleanValue b = this.b.evaluate().val(BooleanValue.class);
        return new BooleanValue(a.value() && b.value());
    }
}
