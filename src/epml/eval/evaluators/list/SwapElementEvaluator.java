package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.Accessor;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "swap $a and $b")
public class SwapElementEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public SwapElementEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        Accessor aAccessor = this.a.evaluate().as(Accessor.class);
        Accessor bAccessor = this.b.evaluate().as(Accessor.class);

        Value temp = bAccessor.get();
        bAccessor.set(aAccessor.get());
        aAccessor.set(temp);

        return new NoneValue();
    }
}
