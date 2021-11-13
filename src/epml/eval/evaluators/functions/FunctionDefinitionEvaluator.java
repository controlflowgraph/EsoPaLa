package epml.eval.evaluators.functions;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.evaluators.util.MultiEvaluator;
import epml.eval.values.FunctionValue;
import epml.eval.values.Value;
import epml.eval.values.VariableAccessor;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(pattern = "a function that receives $parameters and does the following $body")
public class FunctionDefinitionEvaluator implements Evaluator
{
    private final Evaluator parameters;
    private final Evaluator body;

    public FunctionDefinitionEvaluator(Environment env, Evaluator parameters, Evaluator body)
    {
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public Value evaluate()
    {
        List<String> parameters = new ArrayList<>();
        if(this.parameters instanceof MultiEvaluator m)
        {
            for(Evaluator eval : m.getEvaluators())
            {
                parameters.add(eval.evaluate().as(VariableAccessor.class).getVariable());
            }
        }
        else
        {
            parameters.add(this.parameters.evaluate().as(VariableAccessor.class).getVariable());
        }
        return new FunctionValue(parameters, this.body);
    }
}
