package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "the empty list")
public class EmptyListEvaluator implements Evaluator
{
    public EmptyListEvaluator(Environment env)
    {

    }

    @Override
    public Value evaluate()
    {
        return new ListValue(new ArrayList<>(List.of()));
    }
}
