package epl.eval.evaluators.list;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.util.Concatenation;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a concatenated with $b" })
public class ConcatenationEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public ConcatenationEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Concatenation.class, a -> a.concatenate(this.b.val()));
    }
}
