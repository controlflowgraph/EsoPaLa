package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.IndexAccessor;
import epml.eval.values.ListValue;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "$list at $pos")
public class IndexEvaluator implements Evaluator
{
    private final Evaluator list;
    private final Evaluator index;

    public IndexEvaluator(Environment env, Evaluator list, Evaluator index)
    {
        this.list = list;
        this.index = index;
    }

    @Override
    public Value evaluate()
    {
        return new IndexAccessor(this.list.evaluate().val(ListValue.class), this.index.evaluate().val (NumberValue.class));
    }
}
