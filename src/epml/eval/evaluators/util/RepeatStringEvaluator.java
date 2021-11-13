package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.StringValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "repeat $a $b times")
public class RepeatStringEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public RepeatStringEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public Value evaluate()
    {
        StringValue value = this.a.evaluate().val(StringValue.class);
        NumberValue amount = this.b.evaluate().val(NumberValue.class);
        return new StringValue(value.content().repeat((int) amount.value()));
    }
}
