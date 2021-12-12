package epl.eval.evaluators.util;

import epl.eval.Environment;
import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.IteratorValue;
import epl.eval.values.NoneValue;
import epl.eval.values.Value;
import epl.eval.values.VariableLocation;

@LanguagePattern(patterns = { "for each $a in $b $c" })
public class ForEachEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator a;
    private final Evaluator b;
    private final Evaluator c;

    public ForEachEvaluator(Environment env, Evaluator a, Evaluator b, Evaluator c)
    {
        this.env = env;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Value evaluate()
    {
        VariableLocation variable = this.a.as(VariableLocation.class);
        IteratorValue iterator = this.b.val(IteratorValue.class);
        while(iterator.hasNext())
        {
            this.env.push();
            variable.set(iterator.next());
            this.c.evaluate();
            this.env.pop();
        }
        return new NoneValue();
    }
}
