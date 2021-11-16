package epml.eval.evaluators.util.structs;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "while $cond $body")
public class WhileEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator condition;
    private final Evaluator body;

    public WhileEvaluator(Environment env, Evaluator condition, Evaluator body)
    {
        this.env = env;
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Value evaluate()
    {
        this.env.push();
        while(true)
        {
            BooleanValue cond = this.condition.evaluate().val(BooleanValue.class);
            if(!cond.value()) break;
            this.body.evaluate();
        }
        this.env.pop();
        return new NoneValue();
    }
}
