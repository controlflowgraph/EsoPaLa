package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.RangeValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "the range from $a to $b incrementing by $c")
public class RangeEvaluator implements Evaluator
{
    private final Evaluator start;
    private final Evaluator end;
    private final Evaluator increment;

    public RangeEvaluator(Environment env, Evaluator start, Evaluator end, Evaluator increment)
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
        return new RangeValue(start.value(), end.value(), increment.value());
    }
}
