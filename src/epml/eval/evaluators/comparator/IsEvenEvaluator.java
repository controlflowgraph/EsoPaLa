package epml.eval.evaluators.comparator;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "$num is even")
public class IsEvenEvaluator implements Evaluator
{
    private final Evaluator num;

    public IsEvenEvaluator(Environment env, Evaluator num)
    {
        this.num = num;
    }

    @Override
    public Value evaluate()
    {
        NumberValue number = this.num.evaluate().val(NumberValue.class);
        return new BooleanValue(number.value() % 2 == 0);
    }
}
