package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@SingleLanguagePattern(pattern = "$a concatenated with $b")
public class ConcatenationEvaluator implements Evaluator
{
    private final Evaluator list1;
    private final Evaluator list2;

    public ConcatenationEvaluator(Environment env, Evaluator list1, Evaluator list2)
    {
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public Value evaluate()
    {
        ListValue list1 = this.list1.evaluate().val(ListValue.class);
        ListValue list2 = this.list2.evaluate().val(ListValue.class);
        List<Value> values = new ArrayList<>(list1.values());
        values.addAll(list2.values());
        return new ListValue(values);
    }
}
