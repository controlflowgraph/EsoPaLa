package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.*;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "a list containing all elements $var from $source where $c")
public class ListSelectorEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator variable;
    private final Evaluator source;
    private final Evaluator condition;

    public ListSelectorEvaluator(Environment env, Evaluator variable, Evaluator source, Evaluator condition)
    {
        this.env = env;
        this.variable = variable;
        this.source = source;
        this.condition = condition;
    }

    @Override
    public Value evaluate()
    {
        List<Value> elements = new ArrayList<>();
        this.env.push();
        VariableAccessor accessor = this.variable.evaluate().as(VariableAccessor.class);
        ValueIterator iterator = this.source.evaluate().val(ValueIterable.class).iterator();
        while(iterator.hasNext())
        {
            Value value = iterator.next();
            accessor.set(value);
            if(this.condition.evaluate().as(BooleanValue.class).value())
            {
                elements.add(value);
            }
        }
        this.env.pop();
        return new ListValue(elements);
    }
}
