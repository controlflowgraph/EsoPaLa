package epl.parser.code.token;

import epl.parser.util.Token;

public record CodeToken(String token, CodeTokenType type, int position) implements Token<CodeTokenType>
{
    @Override
    public String toString()
    {
        return this.token + " " + this.type + " " + this.position;
    }
}
