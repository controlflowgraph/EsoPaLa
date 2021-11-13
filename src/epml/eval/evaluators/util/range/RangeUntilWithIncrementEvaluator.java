package epml.eval.evaluators.util.range;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.RangeToValue;
import epml.eval.values.RangeUntilValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "the range from $a until $b incrementing by $c")
public class RangeUntilWithIncrementEvaluator implements Evaluator
{
    private final Evaluator start;
    private final Evaluator end;
    private final Evaluator increment;

    public RangeUntilWithIncrementEvaluator(Environment env, Evaluator start, Evaluator end, Evaluator increment)
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
        return new RangeUntilValue(start.value(), end.value(), increment.value());
    }
}
