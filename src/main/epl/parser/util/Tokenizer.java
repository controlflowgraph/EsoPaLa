package epl.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Tokenizer<TokenClass extends Token<TokenType>, TokenType>
{
    private final List<TokenCategory<TokenType>> categories;
    private final List<Matcher> matchers;
    private final TokenFactory<TokenClass, TokenType> factory;
    private final int length;

    public Tokenizer(List<TokenCategory<TokenType>> categories, TokenFactory<TokenClass, TokenType> factory, String text)
    {
        this.categories = categories;
        this.factory = factory;
        this.length = text.length();

        this.matchers = new ArrayList<>();
        for(TokenCategory<TokenType> category : categories)
        {
            this.matchers.add(category.pattern().matcher(text));
        }
    }

    public List<TokenClass> tokenize()
    {
        List<TokenClass> tokens = new ArrayList<>();
        int start = 0;
        while(start < this.length)
        {
            int index = getBestIndex(start);
            if(index == -1)
            {
                start = Integer.MAX_VALUE;
            }
            else
            {
                Matcher matcher = this.matchers.get(index);
                String token = matcher.group();
                TokenType type = this.categories.get(index).type();
                int position = matcher.start();
                tokens.add(this.factory.create(token, type, position));
                start = matcher.end();
            }
        }
        return tokens;
    }

    private int getBestIndex(int start)
    {
        int bestIndex = -1;
        int bestStart = Integer.MAX_VALUE;
        for(int i = 0; i < this.matchers.size(); i++)
        {
            Matcher matcher = this.matchers.get(i);
            if(matcher.find(start))
            {
                int matcherStart = matcher.start();
                if(matcherStart < bestStart)
                {
                    bestIndex = i;
                    bestStart = matcherStart;
                }
            }
        }
        return bestIndex;
    }
}
