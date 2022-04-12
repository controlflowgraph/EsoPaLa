package epl.parser.pattern.token;

import epl.parser.util.TokenFactory;

public class PatternTokenFactory implements TokenFactory<PatternToken, PatternTokenType>
{
    @Override
    public PatternToken create(String token, PatternTokenType type, int position)
    {
        return new PatternToken(token, type, position);
    }
}
