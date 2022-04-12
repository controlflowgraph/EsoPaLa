package epl.parser;

import epl.eval.Environment;
import epl.eval.evaluators.Evaluator;
import epl.eval.LanguagePattern;
import epl.parser.pattern.PatternMatcher;
import epl.parser.pattern.PatternParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record MatcherUnit (Constructor<?> constructor, PatternMatcher matcher, boolean requiresEnvironment)
{
    public Evaluator matches(MatchingContext context)
    {
        Map<String, List<Evaluator>> matches = this.matcher.matches(context);
        if(matches != null)
        {
            Parameter[] parameters = this.constructor.getParameters();
            Object[] arguments = new Object[parameters.length];
            int index = 0;
            if(this.requiresEnvironment)
            {
                arguments[index++] = context.getEnv();
            }
            Map<String, Integer> occ = this.matcher.occurrences();
            for(String name : occ.keySet())
            {
                List<Evaluator> evaluators = matches.getOrDefault(name, List.of());
                Class<?> type = parameters[index].getType();
                if(Evaluator.class.isAssignableFrom(type))
                {
                    arguments[index++] = evaluators.size() == 0 ? null : evaluators.get(0);
                }
                else if(List.class.isAssignableFrom(type))
                {
                    arguments[index++] = evaluators;
                }
            }
            try
            {
                return (Evaluator) this.constructor.newInstance(arguments);
            }
            catch (Exception e)
            {
                throw new RuntimeException("ERROR CREATING THE EVALUATOR! (" + this.constructor.getDeclaringClass().getName() + ")");
            }
        }
        return null;
    }
    public static List<MatcherUnit> getUnits(List<Class<? extends Evaluator>> evaluators)
    {
        List<MatcherUnit> units = new ArrayList<>();
        for (Class<? extends Evaluator> evaluator : evaluators)
        {
            if (!evaluator.isAnnotationPresent(LanguagePattern.class))
            {
                throw new RuntimeException("Evaluator without LanguagePattern annotation is an invalid input! (" + evaluator.getName() + ")");
            }

            LanguagePattern annotation = evaluator.getAnnotation(LanguagePattern.class);
            for (String pattern : annotation.patterns())
            {
                PatternMatcher matcher = PatternParser.parse(pattern);
                List<Class<?>> types = new ArrayList<>();
                matcher.occurrences().forEach((n, o) -> types.add(o == 1 ? Evaluator.class : List.class));

                units.add(get(evaluator, types, matcher));
            }
        }
        return units;
    }

    private static MatcherUnit get(Class<? extends Evaluator> evaluator, List<Class<?>> types, PatternMatcher matcher)
    {
        Constructor<?>[] constructors = evaluator.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors)
        {
            int match = checkConstructorMatch(constructor, types);
            if(match != -1)
            {
                return new MatcherUnit(constructor, matcher, match == 1);
            }
        }
        throw new RuntimeException("NO MATCH BETWEEN PATTERN AND CONSTRUCTORS! " + evaluator.getName());
    }

    private static int checkConstructorMatch(Constructor<?> constructor, List<Class<?>> types)
    {
        Parameter[] parameters = constructor.getParameters();
        if(parameters.length < types.size()) return -1;
        if(parameters.length == 0) return 0;

        int offset = 0;
        // check if the first parameter is the environment (an optional parameter)
        if(Environment.class.isAssignableFrom(parameters[0].getType()))
        {
            offset++;
        }

        if(parameters.length - offset < types.size()) return -1;

        for(int i = offset; i < parameters.length; i++)
        {
            if(i - offset >= types.size() || !types.get(i - offset).isAssignableFrom(parameters[i].getType()))
            {
                return -1;
            }
        }

        return offset;
    }
}
