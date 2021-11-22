package epl.eval.evaluators.util;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.util.Location;
import epl.eval.values.NoneValue;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "let $a be equal to $b" })
public class AssignmentEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public AssignmentEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public Value evaluate()
    {
        Location loc = this.a.as(Location.class);
        loc.set(this.b.val());
        return new NoneValue();
    }
}
