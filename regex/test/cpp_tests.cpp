#include "parse_exception.h"
#include "parser.h"

#include <iostream>
#include <string>
#include <vector>

namespace {
struct TreeTest {
    std::string name;
    std::string input;
    std::string expected;
};

bool expectTree(const TreeTest& test) {
    try {
        Parser parser;
        const Tree tree = parser.parse(test.input);
        const std::string actual = tree.toString();
        if (actual == test.expected) {
            std::cout << "[PASSED] " << test.name << '\n';
            return true;
        }

        std::cout << "[FAILED] " << test.name << ": tree mismatch\n" << "Expected: " << test.expected << '\n' << "Actual:   " << actual << '\n';
        return false;
    } catch (const std::exception& e) {
        std::cout << "[FAILED] " << test.name << ": " << e.what() << '\n';
        return false;
    }
}

bool expectCorrect(const std::string& name, const std::string& input) {
    try {
        Parser parser;
        parser.parse(input);
        std::cout << "[PASSED] " << name << '\n';
        return true;
    } catch (const std::exception& e) {
        std::cout << "[FAILED] " << name << ": " << e.what() << '\n';
        return false;
    }
}

bool expectError(const std::string& name, const std::string& input) {
    try {
        Parser parser;
        parser.parse(input);
        std::cout << "[FAILED] " << name << ": no exception occurred\n";
        return false;
    } catch (const ParseException& e) {
        std::cout << "[PASSED] " << name << ": " << e.what() << '\n';
        return true;
    } catch (const std::exception& e) {
        std::cout << "[FAILED] " << name << ": invalid exception " << e.what() << '\n';
        return false;
    }
}
}

int main() {
    const std::vector<TreeTest> treeTests = {
        {"CorrectnessTreeLetter", "a", "R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(epsilon))"},
        {"CorrectnessTreeChoice", "a|b", "R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(b), C(epsilon)), S1(epsilon)), R1(epsilon))))"},
        {"CorrectnessTreeKleene", "a*", "R0(S0(T(A(a), C(*, C(epsilon))), S1(epsilon)), R1(epsilon))"},
        {"CorrectnessTreeConcatenate", "ab", "R0(S0(T(A(a), C(epsilon)), S1(S0(T(A(b), C(epsilon)), S1(epsilon)))), R1(epsilon))"},
        {"CorrectnessTreeParenthesis", "(a)", "R0(S0(T(A((, R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(epsilon)), )), C(epsilon)), S1(epsilon)), R1(epsilon))"},
        {"TreeABC", "abc", "R0(S0(T(A(a), C(epsilon)), S1(S0(T(A(b), C(epsilon)), S1(S0(T(A(c), C(epsilon)), S1(epsilon)))))), R1(epsilon))"},
        {"TreeParenthesisA", "(a)", "R0(S0(T(A((, R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(epsilon)), )), C(epsilon)), S1(epsilon)), R1(epsilon))"},
        {"TreeXY", "xy", "R0(S0(T(A(x), C(epsilon)), S1(S0(T(A(y), C(epsilon)), S1(epsilon)))), R1(epsilon))"},
        {"TreeAorBorC", "a|b|c", "R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(b), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(c), C(epsilon)), S1(epsilon)), R1(epsilon))))))"},
        {"TreeABstar", "ab*", "R0(S0(T(A(a), C(epsilon)), S1(S0(T(A(b), C(*, C(epsilon))), S1(epsilon)))), R1(epsilon))"},
        {"TreeXorYorZ", "x|y|z", "R0(S0(T(A(x), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(y), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(z), C(epsilon)), S1(epsilon)), R1(epsilon))))))"},
        {"TreeABParenCDStar", "ab(c|d)*", "R0(S0(T(A(a), C(epsilon)), S1(S0(T(A(b), C(epsilon)), S1(S0(T(A((, R0(S0(T(A(c), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(d), C(epsilon)), S1(epsilon)), R1(epsilon)))), )), C(*, C(epsilon))), S1(epsilon)))))), R1(epsilon))"},
        {"TreeDoubleParenthesesA", "((a))", "R0(S0(T(A((, R0(S0(T(A((, R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(epsilon)), )), C(epsilon)), S1(epsilon)), R1(epsilon)), )), C(epsilon)), S1(epsilon)), R1(epsilon))"},
        {"TreeAMultipleStars", "a***", "R0(S0(T(A(a), C(*, C(*, C(*, C(epsilon))))), S1(epsilon)), R1(epsilon))"},
        {"TreeAorBStarC", "a|b*c", "R0(S0(T(A(a), C(epsilon)), S1(epsilon)), R1(|, R0(S0(T(A(b), C(*, C(epsilon))), S1(S0(T(A(c), C(epsilon)), S1(epsilon)))), R1(epsilon))))"}
    };

    const std::vector<std::pair<std::string, std::string>> correct = {
        {"ParsesLongConcatenation", "abcdef"},
        {"ParsesLongChoice", "a|b|c|d|e|f"},
        {"ParsesLongKleene", "a*****"},
        {"ParsesLongParenthesis", "(((((a)))))"},
        {"ParsesMixedComplex", "ab|c*d(e|f)"},
        {"ParsesNestedParenthesisKleene", "((ab)*)*|x"},
        {"ParsesDeeplyNested", "((((a)|(b))|c)*)"},
        {"ParsesMultipleChoiceParenthesis", "(a|b)|(c|(d|e))*"},
        {"ParsesComplexConcatenations", "x|y(z*)w*|abc|(p|q)r"},
        {"ParsesComplicated (1)", "a|b*|c**|(a*|b**|(c*|d)**)*ab"},
        {"ParsesComplicated (2)", "(z(y(x*)|(y|z*))*|(x|y)|z*)**"},
        {"SimpleLetters", "xyz"},
        {"ParenthesizedSingle", "(x)"},
        {"MultiChoice", "a|b|c"},
        {"GroupKleene", "(a|b)*"},
        {"MultipleStars", "z****"},
        {"BranchInsideParenthesis", "a(b|c)*"},
        {"TwoGroups", "(a|b)(c|d)"},
        {"ParenthesisAndStar", "(ab)c*"},
        {"MixedNested", "((ab)*|(cd))*"},
        {"DoubleParenthesis", "((a))"},
        {"ConcatWithChoiceStar", "ab(c|d)*"},
        {"GroupChoiceThenChoice", "(ab|c)*|d"},
        {"Complex", "x|y(z*)w*|abc|(p|q)r"},
        {"GroupChoiceConcatStar", "(xy|(a|b))z*"},
        {"MultiChoiceInOneParenthesis", "(a|b|c|d)"},
        {"NestedChoiceStar", "(a|(b|c)*)*"},
        {"DeepParenthesis", "a(b(c(d(e))))"},
        {"MultipleStarsConcat", "a****b**"},
        {"TripleParenthesis", "(((abc)))"},
        {"MixedFinal", "(a|b)(c)*|(d|e)f*"},
        {"SinglePlus", "z+"},
        {"MultiplePlus", "z++++"},
        {"MixedPlus", "z+x+y+?"},
        {"TrueMixedPlus", "z+x+y+?*"},
        {"Range", "[a-z]"},
        {"SingleQuestion", "a?"},
        {"MultipleQuestion", "a??"},
        {"SingleKleene", "b*"},
        {"MixedKleeneAndPlus", "x*y+"},
        {"ComplexRange", "[a-z]+"},
        {"NestedParentheses", "(a+b)*"},
        {"ComplexExpression", "(a|b)*c+d?"},
        {"RangeWithOperators", "[a-z]|x+"}
    };

    const std::vector<std::pair<std::string, std::string>> incorrect = {
        {"LexerAlphabet", "abcDef"},
        {"LexerDigits", "abcd3f"},
        {"LexerSymbols", "^bcdef"},
        {"StartsWithChoice", "|abcd"},
        {"StartsWithChoiceEmpty", "|"},
        {"StartsWithAsterisk", "*abcd"},
        {"StartsWithAsteriskEmpty", "*"},
        {"StartsWithRightParenthesis", ")abc(d)"},
        {"EndsWithChoice", "a|b|c|"},
        {"EndsWithOpeningParenthesis", "a(bc("},
        {"Empty", ""},
        {"EmptyChoice", "a||b"},
        {"EmptyParenthesis", "a()b"},
        {"UnmatchedOpeningParenthesis", "a(bc(d)e"},
        {"UnmatchedClosingParenthesis", "x(abc)d)e"},
        {"AsteriskAfterLeftParenthesis", "a(*b)"},
        {"AsteriskAfterChoice", "a|b|*"},
        {"ExtraClosingParenthesis", "a(bc))"},
        {"EmptyParenthesisWithStar", "()*"},
        {"OnlyStars", "****"},
        {"StartWithChoiceAndParenthesis", "|(abc)"},
        {"LexerAlphabetCapital", "abcDxyz"},
        {"CapitalLetter", "A"},
        {"Digit", "0abc"},
        {"HatSymbol", "^abc"},
        {"Empty", ""},
        {"ExtraOpeningParenthesis", "((abc)"},
        {"ExtraClosingParenthesis2", "abc)"},
        {"StartWithChoice", "|abc"},
        {"StartWithStar", "*abc"},
        {"EndWithChoice", "abc|"},
        {"EndWithOpeningParenthesis", "abc("},
        {"BadParenthesisOrder", "a)((b)"},
        {"TooManyClosing", "((((a)))))))"},
        {"NotEnoughClosing", "(((ab))("},
        {"EmptyChoice", "a||b"},
        {"StartWithClosingParenthesis", ")("},
        {"EmptyParenthesis", "ab()"},
        {"CaretInside", "abc^def"},
        {"EndWithChoiceAgain", "x|y|z|"},
        {"EmptyAlternative", "(a|)"}
    };

    int passed = 0;
    int total = 0;

    for (const auto& test : treeTests) {
        ++total;
        if (expectTree(test)) {
            ++passed;
        }
    }
    for (const auto& test : correct) {
        ++total;
        if (expectCorrect(test.first, test.second)) {
            ++passed;
        }
    }
    for (const auto& test : incorrect) {
        ++total;
        if (expectError(test.first, test.second)) {
            ++passed;
        }
    }

    std::cout << "\nTESTS: " << total << "\nPassed: " << passed << ", Failed: " << (total - passed) << '\n';
    return passed == total ? 0 : 1;
}
