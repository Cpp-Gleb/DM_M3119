#include "lexer.h"

#include "parse_exception.h"

#include <utility>

Lexer::Lexer(std::string input) : input_(std::move(input)) {}

std::vector<Token> Lexer::tokenize() const {
    if (input_.empty()) {
        throw ParseException("empty regular expression", 0);
    }

    std::vector<Token> tokens;
    tokens.reserve(input_.size() + 1);

    for (std::size_t i = 0; i < input_.size(); ++i) {
        const char ch = input_[i];
        TokenType type;

        if ('a' <= ch && ch <= 'z') {
            type = TokenType::Letter;
        } else {
            switch (ch) {
                case '|':
                    type = TokenType::Choice;
                    break;
                case '(':
                    type = TokenType::LParen;
                    break;
                case ')':
                    type = TokenType::RParen;
                    break;
                case '*':
                    type = TokenType::Kleene;
                    break;
                case '+':
                    type = TokenType::Plus;
                    break;
                case '?':
                    type = TokenType::Question;
                    break;
                case '[':
                    type = TokenType::LBracket;
                    break;
                case ']':
                    type = TokenType::RBracket;
                    break;
                case '-':
                    type = TokenType::Dash;
                    break;
                default:
                    throw ParseException("unexpected character '" + std::string(1, ch) + "'", i);
            }
        }

        tokens.push_back(Token{type, ch, i});
    }

    tokens.push_back(Token{TokenType::End, '\0', input_.size()});
    return tokens;
}
