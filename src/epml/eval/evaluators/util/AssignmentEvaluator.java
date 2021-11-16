package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.Value;
import epml.eval.values.VariableAccessor;

@SingleLanguagePattern(pattern = "let $var be equal to $val")
public class AssignmentEvaluator implements Evaluator
{
    private final Environment env;
    private final Evaluator variable;
    private final Evaluator value;

    public AssignmentEvaluator(Environment env, Evaluator variable, Evaluator value)
    {
        this.env = env;
        this.variable = variable;
        this.value = value;
    }

    @Override
    public Value evaluate()
    {
        VariableAccessor accessor = this.variable.evaluate().as(VariableAccessor.class);
        Value value = this.value.evaluate().val();
        accessor.setLocal(value);
        return value;
    }
}
