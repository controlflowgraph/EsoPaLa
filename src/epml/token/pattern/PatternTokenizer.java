package epml.token.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTokenizer
{
    private static final List<ElementType> TYPES = List.of(
            new ElementType("word", Pattern.compile("([a-zA-Z]+)")),
            new ElementType("sub", Pattern.compile("(\\$[a-zA-Z]+)")),
            new ElementType("symbol", Pattern.compile("([^ \t\n])"))
    );

    public static PatternExpression tokenize(String pattern)
    {
        return getRefinedExpression(getRawElements(pattern));
    }

    private static PatternExpression getRefinedExpression(List<Element> elements)
    {
        return new PatternExpression(elements);
    }

    private static List<Element> getRawElements(String pattern)
    {
        List<Element> elements = new ArrayList<>();
        List<Matcher> matchers = TYPES.stream().map(t -> t.pattern().matcher(pattern)).toList();
        int start = 0;
        int length = pattern.length();
        while(start < length)
        {
            ElementType bestType = null;
            Matcher bestMatcher = null;
            int bestStart = Integer.MAX_VALUE;
            int bestEnd = Integer.MAX_VALUE;
            for(int i = 0; i < TYPES.size(); i++)
            {
                Matcher matcher = matchers.get(i);
                if (matcher.find(start))
                {
                    int matcherStart = matcher.start();
                    if(matcherStart < bestStart)
                    {
                        bestStart = matcherStart;
                        bestEnd = matcher.end();
                        bestType = TYPES.get(i);
                        bestMatcher = matcher;
                    }
                }
            }

            start = bestEnd;
            if(bestType == null) break;

            String content = bestMatcher.group();
            elements.add(switch (bestType.name())
                                 {
                                     case "word", "symbol" -> new WordElement(content);
                                     case "sub" -> new ExpressionElement();
                                     default -> throw new RuntimeException("Unknown element type!");
                                 });
        }
        return elements;
    }
}
