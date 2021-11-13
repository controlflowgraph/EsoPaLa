package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.Accessor;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;
import epml.eval.values.VariableAccessor;

@LanguagePattern(pattern = "set $a to $b")
public class SetVariableEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public SetVariableEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        Accessor accessor = this.a.evaluate().as(Accessor.class);
        Value value = this.b.evaluate().val();
        accessor.set(value);
        return new NoneValue();
    }
}
