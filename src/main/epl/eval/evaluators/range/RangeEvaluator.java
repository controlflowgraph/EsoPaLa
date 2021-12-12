package epl.eval.evaluators.range;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.NumberValue;
import epl.eval.values.Value;
import epl.eval.values.VariableLocation;
import epl.eval.values.range.RangeValue;

@LanguagePattern(patterns = { "the range from $a $mode $c (? incrementing by $d)" })
public class RangeEvaluator implements Evaluator
{
    private final Evaluator start;
    private final Evaluator end;
    private final Evaluator increment;
    private final Evaluator mode;

    public RangeEvaluator(Evaluator start, Evaluator mode, Evaluator end, Evaluator increment)
    {
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.mode = mode;
    }

    @Override
    public Value evaluate()
    {
        NumberValue start = this.start.val(NumberValue.class);
        NumberValue end = this.end.val(NumberValue.class);
        VariableLocation mode = this.mode.as(VariableLocation.class);
        boolean inclusive = mode.getVariable().equals("until");
        double s = Math.min(start.number(), end.number());
        double e = Math.max(start.number(), end.number());
        double v = start.number();
        double i;

        boolean up = start.number() < end.number();
        if(this.increment != null)
        {
            NumberValue increment = this.increment.val(NumberValue.class);
            i = increment.number();
        }
        else
        {
            i = up ? 1 : -1;
        }
        boolean is = up || inclusive;
        boolean ie = !up || inclusive;
        return new RangeValue(s, e, v, i, is, ie);
    }
}