package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.*;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "from $source filter all $var where $c while $cc")
public class TakeWhileWhereEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator source;
    private final Evaluator variable;
    private final Evaluator cond1;
    private final Evaluator cond2;

    public TakeWhileWhereEvaluator(Environment env, Evaluator source, Evaluator variable, Evaluator cond1, Evaluator cond2)
    {
        this.env = env;
        this.source = source;
        this.variable = variable;
        this.cond1 = cond1;
        this.cond2 = cond2;
    }


    @Override
    public Value evaluate()
    {
        List<Value> values = new ArrayList<>();
        this.env.push();
        VariableAccessor accessor = this.variable.evaluate().as(VariableAccessor.class);
        ValueIterator iterator = this.source.evaluate().val(ValueIterable.class).iterator();
        while(iterator.hasNext())
        {
            Value value = iterator.next();
            accessor.set(value);
            if(!this.cond2.evaluate().as(BooleanValue.class).value())
            {
                break;
            }
            if(this.cond1.evaluate().as(BooleanValue.class).value())
            {
                values.add(value);
            }
        }
        this.env.pop();
        return new ListValue(values);
    }
}
