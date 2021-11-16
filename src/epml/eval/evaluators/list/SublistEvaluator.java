package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@SingleLanguagePattern(pattern = "the sublist from $a to $b of $list")
public class SublistEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;
    private final Evaluator list;

    public SublistEvaluator(Environment env, Evaluator a, Evaluator b, Evaluator list)
    {
        this.a = a;
        this.b = b;
        this.list = list;
    }


    @Override
    public Value evaluate()
    {
        NumberValue a = this.a.evaluate().val(NumberValue.class);
        NumberValue b = this.b.evaluate().val(NumberValue.class);
        List<Value> sub = new ArrayList<>();
        ListValue value = this.list.evaluate().val(ListValue.class);
        List<Value> original = value.values();
        int start = (int) a.value();
        int end = (int) b.value();
        for(int i = start; i < end; i++)
        {
            sub.add(original.get(i));
        }
        return new ListValue(sub);
    }
}
