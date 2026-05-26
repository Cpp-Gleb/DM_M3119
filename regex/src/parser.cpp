#include "parser.h"

#include "lexer.h"
#include "parse_exception.h"

#include <utility>

namespace {
Tree leaf(const std::string& label) {
    return Tree(label);
}

Tree node(const std::string& label, std::vector<Tree> children) {
    return Tree(label, std::move(children));
}
}

Tree Parser::parse(const std::string& input) {
    tokens_ = Lexer(input).tokenize();
    position_ = 0;

    Tree result = parseR0();
    if (current().type != TokenType::End) {
        throw ParseException("unexpected token '" + current().text() + "' after complete expression", current().position);
    }
    return result;
}

Tree Parser::parseR0() {
    return node("R0", {parseS0(), parseR1()});
}

Tree Parser::parseR1() {
    if (accept(TokenType::Choice)) {
        return node("R1", {leaf("|"), parseR0()});
    }
    return node("R1", {leaf("epsilon")});
}

Tree Parser::parseS0() {
    return node("S0", {parseT(), parseS1()});
}

Tree Parser::parseS1() {
    if (startsA()) {
        return node("S1", {parseS0()});
    }
    return node("S1", {leaf("epsilon")});
}

Tree Parser::parseT() {
    return node("T", {parseA(), parseC()});
}

Tree Parser::parseA() {
    const Token token = current();

    if (token.type == TokenType::Letter) {
        ++position_;
        return node("A", {leaf(std::string(1, token.value))});
    }

    if (token.type == TokenType::LParen) {
        ++position_;
        Tree inner = parseR0();
        expect(TokenType::RParen, ")");
        return node("A", {leaf("("), std::move(inner), leaf(")")});
    }

    if (token.type == TokenType::LBracket) {
        return parseRange();
    }

    throw ParseException("expected letter, '(' or '[', got '" + token.text() + "'", token.position);
}

Tree Parser::parseC() {
    if (accept(TokenType::Kleene)) {
        return node("C", {leaf("*"), parseC()});
    }
    if (accept(TokenType::Plus)) {
        return node("C", {leaf("+"), parseC()});
    }
    if (accept(TokenType::Question)) {
        return node("C", {leaf("?"), parseC()});
    }
    return node("C", {leaf("epsilon")});
}

Tree Parser::parseRange() {
    expect(TokenType::LBracket, "[");
    Token from = expectLetter();
    expect(TokenType::Dash, "-");
    Token to = expectLetter();
    if (from.value > to.value) {
        throw ParseException("invalid range '" + std::string(1, from.value) + "-" + std::string(1, to.value) + "'", from.position);
    }
    expect(TokenType::RBracket, "]");

    return node("A", {
        leaf("["),
        leaf(std::string(1, from.value)),
        leaf("-"),
        leaf(std::string(1, to.value)),
        leaf("]")
    });
}

bool Parser::startsA() const {
    const TokenType type = current().type;
    return type == TokenType::Letter || type == TokenType::LParen || type == TokenType::LBracket;
}

bool Parser::accept(TokenType type) {
    if (current().type == type) {
        ++position_;
        return true;
    }
    return false;
}

Token Parser::expect(TokenType type, const std::string& expected) {
    const Token token = current();
    if (token.type != type) {
        throw ParseException("expected '" + expected + "', got '" + token.text() + "'", token.position);
    }
    ++position_;
    return token;
}

Token Parser::expectLetter() {
    const Token token = current();
    if (token.type != TokenType::Letter) {
        throw ParseException("expected letter, got '" + token.text() + "'", token.position);
    }
    ++position_;
    return token;
}

const Token& Parser::current() const {
    return tokens_[position_];
}
