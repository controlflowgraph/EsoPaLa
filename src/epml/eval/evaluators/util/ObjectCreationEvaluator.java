package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.Value;
import epml.eval.values.ObjectValue;

@LanguagePattern(pattern = "a new object")
public class ObjectCreationEvaluator implements Evaluator
{
    public ObjectCreationEvaluator(Environment env)
    {

    }

    @Override
    public Value evaluate()
    {
        return new ObjectValue();
    }
}
