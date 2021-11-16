package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "append $val to $list")
public class AppendAfterEvaluator implements Evaluator
{
    private final Evaluator value;
    private final Evaluator list;

    public AppendAfterEvaluator(Environment env, Evaluator value, Evaluator list)
    {
        this.value = value;
        this.list = list;
    }

    @Override
    public Value evaluate()
    {
        Value value = this.value.evaluate().val();
        ListValue list = this.list.evaluate().val(ListValue.class);
        list.values().add(value);
        return new NoneValue();
    }
}
