package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.*;

import java.util.ArrayList;
import java.util.List;

@SingleLanguagePattern(pattern = "only select $var from $source where $cond")
public class ListFilterEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator variable;
    private final Evaluator source;
    private final Evaluator condition;

    public ListFilterEvaluator(Environment env, Evaluator variable, Evaluator source, Evaluator condition)
    {
        this.env = env;
        this.variable = variable;
        this.source = source;
        this.condition = condition;
    }

    @Override
    public Value evaluate()
    {
        VariableAccessor accessor = this.variable.evaluate().as(VariableAccessor.class);
        ValueIterator iterator = this.source.evaluate().val(ValueIterable.class).iterator();
        List<Value> elements = new ArrayList<>();
        while(iterator.hasNext())
        {
            Value val = iterator.next();
            accessor.set(val);
            BooleanValue result = this.condition.evaluate().val(BooleanValue.class);
            if(result.value())
            {
                elements.add(val);
            }
        }
        return new ListValue(elements);
    }
}
