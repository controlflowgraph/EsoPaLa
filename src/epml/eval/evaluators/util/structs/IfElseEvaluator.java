package epml.eval.evaluators.util.structs;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "if $cond then $body otherwise $e")
public class IfElseEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator cond;
    private final Evaluator body;
    private final Evaluator otherwise;

    public IfElseEvaluator(Environment env, Evaluator cond, Evaluator body, Evaluator otherwise)
    {
        this.env = env;
        this.cond = cond;
        this.body = body;
        this.otherwise = otherwise;
    }

    @Override
    public Value evaluate()
    {
        Value val;
        this.env.push();
        BooleanValue cond = this.cond.evaluate().val(BooleanValue.class);
        if(cond.value())
        {
            val = this.body.evaluate();
        }
        else
        {
            val = this.otherwise.evaluate();
        }
        this.env.pop();
        return val;
    }
}
