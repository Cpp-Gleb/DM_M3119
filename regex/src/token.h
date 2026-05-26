#pragma once

#include <cstddef>
#include <string>

enum class TokenType {
    Letter,
    Choice,
    LParen,
    RParen,
    Kleene,
    Plus,
    Question,
    LBracket,
    RBracket,
    Dash,
    End
};

struct Token {
    TokenType type;
    char value;
    std::size_t position;

    std::string text() const {
        if (type == TokenType::End) {
            return "<end>";
        }
        return std::string(1, value);
    }
};
