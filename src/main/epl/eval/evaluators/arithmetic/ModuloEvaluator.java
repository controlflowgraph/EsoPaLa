package epl.eval.evaluators.arithmetic;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.operations.arithmetic.Addition;
import epl.eval.operations.arithmetic.Modulo;
import epl.eval.values.Value;

@LanguagePattern(patterns = { "$a modulo $b" })
public class ModuloEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public ModuloEvaluator(Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Value evaluate()
    {
        return this.a.val().op(Modulo.class, a -> a.mod(this.b.val()));
    }
}
