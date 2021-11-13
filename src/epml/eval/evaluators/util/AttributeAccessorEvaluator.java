package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.AttributeAccessor;
import epml.eval.values.Value;
import epml.eval.values.ObjectValue;
import epml.eval.values.VariableAccessor;

@LanguagePattern(pattern = "$a of $b")
public class AttributeAccessorEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public AttributeAccessorEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public Value evaluate()
    {
        VariableAccessor accessor = this.a.evaluate().as(VariableAccessor.class);
        ObjectValue object = this.b.evaluate().val(ObjectValue.class);

        return new AttributeAccessor(accessor.getVariable(), object);
    }
}
