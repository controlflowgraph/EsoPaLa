package epml.eval.evaluators.util.structs;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "execute $pre while $cond $after")
public class DoWhileDoEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator pre;
    private final Evaluator cond;
    private final Evaluator after;

    public DoWhileDoEvaluator(Environment env, Evaluator pre, Evaluator cond, Evaluator after)
    {
        this.env = env;
        this.pre = pre;
        this.cond = cond;
        this.after = after;
    }


    @Override
    public Value evaluate()
    {
        this.env.push();
        while(true)
        {
            this.pre.evaluate();
            BooleanValue bool = this.cond.evaluate().val(BooleanValue.class);
            if(!bool.value()) break;
            this.after.evaluate();
        }
        this.env.pop();
        return new NoneValue();
    }
}
