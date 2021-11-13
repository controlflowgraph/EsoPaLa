package epml.token.pattern;

import epml.token.code.Token;

import java.util.List;

public record PatternExpression(List<Element> elements)
{
    public boolean matches(List<Token> tokens)
    {
        if(tokens.size() != elements.size()) return false;

        for(int i = 0; i < tokens.size(); i++)
        {
            if(!this.elements.get(i).matches(tokens.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}
