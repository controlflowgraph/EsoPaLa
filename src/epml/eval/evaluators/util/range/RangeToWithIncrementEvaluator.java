package epml.eval.evaluators.util.range;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.RangeToValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "the range from $a to $b incrementing by $c")
public class RangeToWithIncrementEvaluator implements Evaluator
{
    private final Evaluator start;
    private final Evaluator end;
    private final Evaluator increment;

    public RangeToWithIncrementEvaluator(Environment env, Evaluator start, Evaluator end, Evaluator increment)
    {
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    public Value evaluate()
    {
        NumberValue start = this.start.evaluate().val(NumberValue.class);
        NumberValue end = this.end.evaluate().val(NumberValue.class);
        NumberValue increment = this.increment.evaluate().val(NumberValue.class);
        return new RangeToValue(start.value(), end.value(), increment.value());
    }
}
