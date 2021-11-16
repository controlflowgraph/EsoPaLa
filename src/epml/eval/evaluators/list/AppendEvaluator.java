package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.MultiLanguagePattern;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

@MultiLanguagePattern(patterns = {"$val append $val"})
public class AppendEvaluator implements Evaluator
{
    private final Evaluator list;
    private final Evaluator element;

    public AppendEvaluator(Environment env, Evaluator list, Evaluator element)
    {
        this.list = list;
        this.element = element;
    }

    @Override
    public Value evaluate()
    {
        ListValue list = this.list.evaluate().val(ListValue.class);
        list.values().add(this.element.evaluate());
        return list;
    }
}
