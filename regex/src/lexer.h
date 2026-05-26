#pragma once

#include "token.h"

#include <string>
#include <vector>

class Lexer {
public:
    explicit Lexer(std::string input);

    std::vector<Token> tokenize() const;

private:
    std::string input_;
};
