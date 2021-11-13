package epml.eval.evaluators.util.structs;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.*;

@LanguagePattern(pattern = "for each $var in $source do $body")
public class ForEachEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator variable;
    private final Evaluator source;
    private final Evaluator body;

    public ForEachEvaluator(Environment env, Evaluator variable, Evaluator source, Evaluator body)
    {
        this.env = env;
        this.variable = variable;
        this.source = source;
        this.body = body;
    }

    @Override
    public Value evaluate()
    {
        VariableAccessor accessor = this.variable.evaluate().as(VariableAccessor.class);
        ValueIterator iterator = this.source.evaluate().val(ValueIterable.class).iterator();

        this.env.push();
        while(iterator.hasNext())
        {
            accessor.setLocal(iterator.next());
            this.body.evaluate();
        }
        this.env.pop();

        return new NoneValue();
    }
}
