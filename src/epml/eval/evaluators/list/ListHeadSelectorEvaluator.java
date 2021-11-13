package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.*;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "a list containing all elements $var from $source where $c while $cc")
public class ListHeadSelectorEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator variable;
    private final Evaluator source;
    private final Evaluator condition1;
    private final Evaluator condition2;

    public ListHeadSelectorEvaluator(Environment env, Evaluator variable, Evaluator source, Evaluator condition1, Evaluator condition2)
    {
        this.env = env;
        this.variable = variable;
        this.source = source;
        this.condition1 = condition1;
        this.condition2 = condition2;
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
            if(!this.condition2.evaluate().as(BooleanValue.class).value())
            {
                break;
            }
            if(this.condition1.evaluate().as(BooleanValue.class).value())
            {
                elements.add(value);
            }
        }
        this.env.pop();
        return new ListValue(elements);
    }
}
