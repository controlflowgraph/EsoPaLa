package epl.parser.util;

import java.util.regex.Pattern;

public record TokenCategory<TokenType>(Pattern pattern, TokenType type)
{
}
