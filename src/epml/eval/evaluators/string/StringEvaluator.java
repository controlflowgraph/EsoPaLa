package epml.eval.evaluators.string;

import epml.eval.Evaluator;
import epml.eval.values.StringValue;
import epml.eval.values.Value;

public class StringEvaluator implements Evaluator
{
    private final String content;

    public StringEvaluator(String content)
    {
        this.content = content.substring(1, content.length() - 1);
    }

    @Override
    public Value evaluate()
    {
        return new StringValue(this.content);
    }
}
