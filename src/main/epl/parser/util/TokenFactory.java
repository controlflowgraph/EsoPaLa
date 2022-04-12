package epl.parser.util;

public interface TokenFactory <TokenClass, TokenType>
{
    TokenClass create(String token, TokenType type, int position);
}
