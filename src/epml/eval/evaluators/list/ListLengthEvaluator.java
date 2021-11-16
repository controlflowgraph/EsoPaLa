package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "the length of $list")
public class ListLengthEvaluator implements Evaluator
{
    private final Evaluator list;

    public ListLengthEvaluator(Environment env, Evaluator list)
    {
        this.list = list;
    }

    @Override
    public Value evaluate()
    {
        return new NumberValue(this.list.evaluate().val(ListValue.class).values().size());
    }
}
