package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "a list only containing $a")
public class SingletonListEvaluator implements Evaluator
{
    private final Evaluator value1;

    public SingletonListEvaluator(Environment env, Evaluator value1)
    {
        this.value1 = value1;
    }

    @Override
    public Value evaluate()
    {
        List<Value> list = new ArrayList<>();
        list.add(this.value1.evaluate().val());
        return new ListValue(list);
    }
}
