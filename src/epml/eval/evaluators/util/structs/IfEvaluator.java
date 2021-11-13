package epml.eval.evaluators.util.structs;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "if $cond then do $body")
public class IfEvaluator implements Evaluator
{
    private final Evaluator cond;
    private final Evaluator body;

    public IfEvaluator(Environment env, Evaluator cond, Evaluator body)
    {
        this.cond = cond;
        this.body = body;
    }

    @Override
    public Value evaluate()
    {
        BooleanValue val = this.cond.evaluate().val(BooleanValue.class);
        if(val.value())
        {
            this.body.evaluate();
        }
        return new NoneValue();
    }
}
