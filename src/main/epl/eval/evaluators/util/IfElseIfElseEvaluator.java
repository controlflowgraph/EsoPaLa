package epl.eval.evaluators.util;

import epl.eval.Environment;
import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.BooleanValue;
import epl.eval.values.NoneValue;
import epl.eval.values.Value;
import epl.eval.values.VariableLocation;

import java.util.List;

@LanguagePattern(patterns = { "if $cond then $body (* in all other cases if $cond then $body) (? otherwise $fallback)" })
public class IfElseIfElseEvaluator implements Evaluator
{
    private final Environment env;
    private final List<Evaluator> conditions;
    private final List<Evaluator> bodies;
    private final Evaluator fallback;

    public IfElseIfElseEvaluator(Environment env, List<Evaluator> conditions, List<Evaluator> bodies, Evaluator fallback)
    {
        this.env = env;
        this.conditions = conditions;
        this.bodies = bodies;
        this.fallback = fallback;
    }

    @Override
    public Value evaluate()
    {
        for(int i = 0; i < this.conditions.size(); i++)
        {
            BooleanValue value = this.conditions.get(i).val(BooleanValue.class);
            if(value.value())
            {
                this.env.push();
                this.bodies.get(i).evaluate();
                this.env.pop();
                return new NoneValue();
            }
        }
        if(this.fallback != null)
        {
            this.env.push();
            this.fallback.evaluate();
            this.env.pop();
        }
        return new NoneValue();
    }
}
