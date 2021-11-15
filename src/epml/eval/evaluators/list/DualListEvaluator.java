package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "a list containing $a and $c")
public class DualListEvaluator implements Evaluator
{
    private final Evaluator value1;
    private final Evaluator value2;

    public DualListEvaluator(Environment env, Evaluator value1, Evaluator value2)
    {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public Value evaluate()
    {
        List<Value> list = new ArrayList<>();
        list.add(this.value1.evaluate().val());
        list.add(this.value2.evaluate().val());
        return new ListValue(list);
    }
}