package epml.eval.evaluators.arithmetic;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "$a plus $b")
public class AdditionEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator a;
    private final Evaluator b;

    public AdditionEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.env = env;
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        NumberValue a = this.a.evaluate().val(NumberValue.class);
        NumberValue b = this.b.evaluate().val(NumberValue.class);

        return new NumberValue(a.value() + b.value());
    }
}
