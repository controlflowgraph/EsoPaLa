package epl.parser.code.token;

import epl.parser.util.TokenFactory;

public class CodeTokenFactory implements TokenFactory<CodeToken, CodeTokenType>
{
    @Override
    public CodeToken create(String token, CodeTokenType type, int position)
    {
        return new CodeToken(token, type, position);
    }
}
