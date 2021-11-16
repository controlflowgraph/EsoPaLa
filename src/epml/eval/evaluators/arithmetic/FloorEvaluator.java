package epml.eval.evaluators.arithmetic;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "$v floored")
public class FloorEvaluator implements Evaluator
{
    private final Evaluator v;

    public FloorEvaluator(Environment env, Evaluator v)
    {
        this.v = v;
    }


    @Override
    public Value evaluate()
    {
        return new NumberValue(Math.floor(this.v.evaluate().val(NumberValue.class).value()));
    }
}
