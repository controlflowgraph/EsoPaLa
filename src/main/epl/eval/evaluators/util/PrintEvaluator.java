package epl.eval.evaluators.util;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.NoneValue;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "print $v" })
public class PrintEvaluator implements Evaluator
{
    private final Evaluator v;

    public PrintEvaluator(Evaluator v)
    {
        this.v = v;
    }

    @Override
    public Value evaluate()
    {
        System.out.println(this.v.val());
        return new NoneValue();
    }
}
