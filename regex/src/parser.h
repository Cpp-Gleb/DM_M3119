#pragma once

#include "token.h"
#include "tree.h"

#include <string>
#include <vector>

class Parser {
public:
    Tree parse(const std::string& input);

private:
    Tree parseR0();
    Tree parseR1();
    Tree parseS0();
    Tree parseS1();
    Tree parseT();
    Tree parseA();
    Tree parseC();
    Tree parseRange();

    bool startsA() const;
    bool accept(TokenType type);
    Token expect(TokenType type, const std::string& expected);
    Token expectLetter();
    const Token& current() const;

    std::vector<Token> tokens_;
    std::size_t position_ = 0;
};
