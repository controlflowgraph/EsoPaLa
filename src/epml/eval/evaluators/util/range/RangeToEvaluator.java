package epml.eval.evaluators.util.range;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.RangeToValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "the range from $a to $b")
public class RangeToEvaluator implements Evaluator
{
    private final Evaluator start;
    private final Evaluator end;

    public RangeToEvaluator(Environment env, Evaluator start, Evaluator end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public Value evaluate()
    {
        NumberValue start = this.start.evaluate().val(NumberValue.class);
        NumberValue end = this.end.evaluate().val(NumberValue.class);
        return new RangeToValue(start.value(), end.value(), 1);
    }
}
