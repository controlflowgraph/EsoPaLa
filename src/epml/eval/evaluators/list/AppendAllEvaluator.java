package epml.eval.evaluators.list;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.ListValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "$val append all from $val")
public class AppendAllEvaluator implements Evaluator
{
    private final Evaluator list1;
    private final Evaluator list2;

    public AppendAllEvaluator(Environment env, Evaluator list1, Evaluator list2)
    {
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public Value evaluate()
    {
        ListValue list1 = this.list1.evaluate().val(ListValue.class);
        ListValue list2 = this.list2.evaluate().val(ListValue.class);
        list1.values().addAll(list2.values());
        return list1;
    }

//    @LanguagePattern(pattern = "$val append all $val")
//    public static Function<Void, Value> get(Environment env, Evaluator a, Evaluator b)
//    {
//        return (_a) -> {
//            ListValue list1 = Evaluator.cast(a.evaluate(), ListValue.class);
//            ListValue list2 = Evaluator.cast(b.evaluate(), ListValue.class);
//            list1.values().addAll(list2.values());
//            return list1;
//        };
//    }
}
