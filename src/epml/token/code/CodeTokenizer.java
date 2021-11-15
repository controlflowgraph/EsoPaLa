package epml.token.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeTokenizer
{
    private static final List<TokenType> TYPES = List.of(
            new TokenType("word", Pattern.compile("([a-zA-Z][a-zA-Z0-9]*)")),
            new TokenType("number", Pattern.compile("([0-9]+(\\.[0-9]+)?)")),
            new TokenType("string", Pattern.compile("\"(\\\\.|.)*?\"")),
            new TokenType("parentheses", Pattern.compile("([()])")),
            new TokenType("dot", Pattern.compile("(\\.)")),
            new TokenType("symbol", Pattern.compile("([^ \t\r\n]+)"))
    );

    public static List<GroupToken> tokenize(String text)
    {
        return getRefinedTokens(getRawTokens(text));
    }

    private static List<GroupToken> getRefinedTokens(List<Token> raw)
    {
        List<GroupToken> expressions = new ArrayList<>();
        for(int i = 0; i < raw.size(); i++)
        {
            Stack<List<Token>> scopes = new Stack<>();
            scopes.push(new ArrayList<>());
            for(; i < raw.size(); i++)
            {
                Token token = raw.get(i);
                if(token instanceof DotToken)
                {
                    if(scopes.size() == 1)
                    {
                        List<Token> scope = scopes.pop();
                        expressions.add(new GroupToken(scope));
                        break;
                    }
                    else
                    {
                        List<Token> scope = scopes.pop();
                        scopes.peek().add(new GroupToken(scope));
                        scopes.push(new ArrayList<>());
                    }
                }
                else if(token instanceof ParenthesesToken p)
                {
                    if(p.opening())
                    {
                        scopes.push(new ArrayList<>());
                    }
                    else
                    {
                        List<Token> scope = scopes.pop();
                        if(scope.size() > 0)
                        {
                            scopes.peek().add(new GroupToken(scope));
                        }
                    }
                }
                else if(token instanceof WordToken w) scopes.peek().add(w);
                else if(token instanceof NumberToken n) scopes.peek().add(n);
                else if(token instanceof StringToken s) scopes.peek().add(s);
                else throw new RuntimeException("Unsupported token type in refinement!");
            }
        }
        return expressions;
    }

    private static List<Token> getRawTokens(String text)
    {
        List<Token> tokens = new ArrayList<>();
        List<Matcher> matchers = TYPES.stream().map(t -> t.pattern().matcher(text)).toList();
        int start = 0;
        int length = text.length();
        while(start < length)
        {
            TokenType bestType = null;
            Matcher bestMatcher = null;
            int bestStart = Integer.MAX_VALUE;
            int bestEnd = Integer.MAX_VALUE;
            for(int i = 0; i < TYPES.size(); i++)
            {
                Matcher matcher = matchers.get(i);
                if(matcher.find(start))
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
            tokens.add(switch (bestType.name())
                               {
                                   case "word", "symbol" -> new WordToken(content);
                                   case "number" -> new NumberToken(content);
                                   case "string" -> new StringToken(content);
                                   case "parentheses" -> new ParenthesesToken(content.equals("("));
                                   case "dot" -> new DotToken();
                                   default -> throw new RuntimeException("Unknown epml.token type!");
                               });
        }
        return tokens;
    }
}
