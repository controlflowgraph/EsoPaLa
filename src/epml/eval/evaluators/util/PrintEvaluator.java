package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "print $val")
public class  PrintEvaluator implements Evaluator
{
    private final Evaluator value;

    public PrintEvaluator(Environment env, Evaluator value)
    {
        this.value = value;
    }

    @Override
    public Value evaluate()
    {
        Value result = this.value.evaluate().val();
        System.out.println(result);
        return new NoneValue();
    }
}
