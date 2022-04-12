package epl.parser.pattern.token;

import epl.parser.util.Token;

public record PatternToken(String token, PatternTokenType type, int position) implements Token<PatternTokenType>
{

}
