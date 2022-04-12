package epl.eval.evaluators.list;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.ListValue;
import epl.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(patterns = { "the empty list", "a list only containing $a", "a list containing $a (* , $a) and $a" })
public class ListEvaluator implements Evaluator
{
    private final List<Evaluator> evaluators;

    public ListEvaluator()
    {
        this.evaluators = List.of();
    }

    public ListEvaluator(Evaluator a)
    {
        this.evaluators = List.of(a);
    }

    public ListEvaluator(List<Evaluator> a)
    {
        this.evaluators = a;
    }

    @Override
    public Value evaluate()
    {
        List<Value> values = new ArrayList<>();
        this.evaluators.forEach(e -> values.add(e.evaluate()));
        return new ListValue(values);
    }
}
