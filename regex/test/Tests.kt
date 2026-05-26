package regex.test

import structure.Tree
import utils.*

@Suppress("DuplicatedCode")
fun main() {
    val me = Test.me

    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(
                me.R1,
                Tree.Leaf(me.CHOICE),
                Tree.InnerNode(
                    me.R0,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    ),
                    Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(
                        me.C,
                        Tree.Leaf(me.KLEENE),
                        Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                    )
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(
                        me.A,
                        Tree.Leaf(me.LPAREN),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        ),
                        Tree.Leaf(me.RPAREN)
                    ),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeABC",
        "abc",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(
                            me.S1,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('c'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            )
                        )
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeParenthesisA",
        "(a)",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(
                        me.A,
                        Tree.Leaf(me.LPAREN),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        ),
                        Tree.Leaf(me.RPAREN)
                    ),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeXY",
        "xy",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('x'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('y'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeAorBorC",
        "a|b|c",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(
                me.R1,
                Tree.Leaf(me.CHOICE),
                Tree.InnerNode(
                    me.R0,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    ),
                    Tree.InnerNode(
                        me.R1,
                        Tree.Leaf(me.CHOICE),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('c'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        )
                    )
                )
            )
        )
    )

    Test.CorrectnessTest(
        "TreeABstar",
        "ab*",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(
                                me.C,
                                Tree.Leaf(me.KLEENE),
                                Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                            )
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeXorYorZ",
        "x|y|z",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('x'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(
                me.R1,
                Tree.Leaf(me.CHOICE),
                Tree.InnerNode(
                    me.R0,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('y'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    ),
                    Tree.InnerNode(
                        me.R1,
                        Tree.Leaf(me.CHOICE),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('z'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        )
                    )
                )
            )
        )
    )

    Test.CorrectnessTest(
        "TreeABParenCDStar",
        "ab(c|d)*",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(
                            me.S1,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(
                                        me.A,
                                        Tree.Leaf(me.LPAREN),
                                        Tree.InnerNode(
                                            me.R0,
                                            Tree.InnerNode(
                                                me.S0,
                                                Tree.InnerNode(
                                                    me.T,
                                                    Tree.InnerNode(me.A, Tree.Leaf(AT('c'))),
                                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                                ),
                                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                                            ),
                                            Tree.InnerNode(
                                                me.R1,
                                                Tree.Leaf(me.CHOICE),
                                                Tree.InnerNode(
                                                    me.R0,
                                                    Tree.InnerNode(
                                                        me.S0,
                                                        Tree.InnerNode(
                                                            me.T,
                                                            Tree.InnerNode(me.A, Tree.Leaf(AT('d'))),
                                                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                                        ),
                                                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                                                    ),
                                                    Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                                                )
                                            )
                                        ),
                                        Tree.Leaf(me.RPAREN)
                                    ),
                                    Tree.InnerNode(
                                        me.C,
                                        Tree.Leaf(me.KLEENE),
                                        Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                    )
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            )
                        )
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeDoubleParenthesesA",
        "((a))",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(
                        me.A,
                        Tree.Leaf(me.LPAREN),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(
                                        me.A,
                                        Tree.Leaf(me.LPAREN),
                                        Tree.InnerNode(
                                            me.R0,
                                            Tree.InnerNode(
                                                me.S0,
                                                Tree.InnerNode(
                                                    me.T,
                                                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                                ),
                                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                                            ),
                                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                                        ),
                                        Tree.Leaf(me.RPAREN)
                                    ),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        ),
                        Tree.Leaf(me.RPAREN)
                    ),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeAMultipleStars",
        "a***",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(
                        me.C,
                        Tree.Leaf(me.KLEENE),
                        Tree.InnerNode(
                            me.C,
                            Tree.Leaf(me.KLEENE),
                            Tree.InnerNode(
                                me.C,
                                Tree.Leaf(me.KLEENE),
                                Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                            )
                        )
                    )
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest(
        "TreeAorBStarC",
        "a|b*c",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(
                me.R1,
                Tree.Leaf(me.CHOICE),
                Tree.InnerNode(
                    me.R0,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(
                                me.C,
                                Tree.Leaf(me.KLEENE),
                                Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                            )
                        ),
                        Tree.InnerNode(
                            me.S1,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('c'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            )
                        )
                    ),
                    Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                )
            )
        )
    )


    Test.CorrectnessTest("ParsesLongConcatenation", "abcdef")
    Test.CorrectnessTest("ParsesLongChoice", "a|b|c|d|e|f")
    Test.CorrectnessTest("ParsesLongKleene", "a*****")
    Test.CorrectnessTest("ParsesLongParenthesis", "(((((a)))))")
    Test.CorrectnessTest("ParsesMixedComplex", "ab|c*d(e|f)")
    Test.CorrectnessTest("ParsesNestedParenthesisKleene", "((ab)*)*|x")
    Test.CorrectnessTest("ParsesDeeplyNested", "((((a)|(b))|c)*)")
    Test.CorrectnessTest("ParsesMultipleChoiceParenthesis", "(a|b)|(c|(d|e))*")
    Test.CorrectnessTest("ParsesComplexConcatenations", "x|y(z*)w*|abc|(p|q)r")
    Test.CorrectnessTest("ParsesComplicated (1)", "a|b*|c**|(a*|b**|(c*|d)**)*ab")
    Test.CorrectnessTest("ParsesComplicated (2)", "(z(y(x*)|(y|z*))*|(x|y)|z*)**")
    Test.CorrectnessTest("SimpleLetters", "xyz")
    Test.CorrectnessTest("ParenthesizedSingle", "(x)")
    Test.CorrectnessTest("MultiChoice", "a|b|c")
    Test.CorrectnessTest("GroupKleene", "(a|b)*")
    Test.CorrectnessTest("MultipleStars", "z****")
    Test.CorrectnessTest("BranchInsideParenthesis", "a(b|c)*")
    Test.CorrectnessTest("TwoGroups", "(a|b)(c|d)")
    Test.CorrectnessTest("ParenthesisAndStar", "(ab)c*")
    Test.CorrectnessTest("MixedNested", "((ab)*|(cd))*")
    Test.CorrectnessTest("DoubleParenthesis", "((a))")
    Test.CorrectnessTest("ConcatWithChoiceStar", "ab(c|d)*")
    Test.CorrectnessTest("GroupChoiceThenChoice", "(ab|c)*|d")
    Test.CorrectnessTest("Complex", "x|y(z*)w*|abc|(p|q)r")
    Test.CorrectnessTest("GroupChoiceConcatStar", "(xy|(a|b))z*")
    Test.CorrectnessTest("MultiChoiceInOneParenthesis", "(a|b|c|d)")
    Test.CorrectnessTest("NestedChoiceStar", "(a|(b|c)*)*")
    Test.CorrectnessTest("DeepParenthesis", "a(b(c(d(e))))")
    Test.CorrectnessTest("MultipleStarsConcat", "a****b**")
    Test.CorrectnessTest("TripleParenthesis", "(((abc)))")
    Test.CorrectnessTest("MixedFinal", "(a|b)(c)*|(d|e)f*")
    Test.CorrectnessTest("SinglePlus", "z+")
    Test.CorrectnessTest("MultiplePlus", "z++++")
    Test.CorrectnessTest("MixedPlus", "z+x+y+?")
    Test.CorrectnessTest("TrueMixedPlus", "z+x+y+?*")
    Test.CorrectnessTest("Range", "[a-z]")
    Test.CorrectnessTest("SingleQuestion", "a?")
    Test.CorrectnessTest("MultipleQuestion", "a??")
    Test.CorrectnessTest("SingleKleene", "b*")
    Test.CorrectnessTest("MixedKleeneAndPlus", "x*y+")
    Test.CorrectnessTest("ComplexRange", "[a-z]+")
    Test.CorrectnessTest("NestedParentheses", "(a+b)*")
    Test.CorrectnessTest("ComplexExpression", "(a|b)*c+d?")
    Test.CorrectnessTest("RangeWithOperators", "[a-z]|x+")



    Test.ParseExceptionTest("LexerAlphabet", "abcDef")
    Test.ParseExceptionTest("LexerDigits", "abcd3f")
    Test.ParseExceptionTest("LexerSymbols", "^bcdef")
    Test.ParseExceptionTest("StartsWithChoice", "|abcd")
    Test.ParseExceptionTest("StartsWithChoiceEmpty", "|")
    Test.ParseExceptionTest("StartsWithAsterisk", "*abcd")
    Test.ParseExceptionTest("StartsWithAsteriskEmpty", "*")
    Test.ParseExceptionTest("StartsWithRightParenthesis", ")abc(d)")
    Test.ParseExceptionTest("EndsWithChoice", "a|b|c|")
    Test.ParseExceptionTest("EndsWithOpeningParenthesis", "a(bc(")
    Test.ParseExceptionTest("Empty", "")
    Test.ParseExceptionTest("EmptyChoice", "a||b")
    Test.ParseExceptionTest("EmptyParenthesis", "a()b")
    Test.ParseExceptionTest("UnmatchedOpeningParenthesis", "a(bc(d)e")
    Test.ParseExceptionTest("UnmatchedClosingParenthesis", "x(abc)d)e")
    Test.ParseExceptionTest("AsteriskAfterLeftParenthesis", "a(*b)")
    Test.ParseExceptionTest("AsteriskAfterChoice", "a|b|*")
    Test.ParseExceptionTest("ExtraClosingParenthesis", "a(bc))")
    Test.ParseExceptionTest("EmptyParenthesisWithStar", "()*")
    Test.ParseExceptionTest("OnlyStars", "****")
    Test.ParseExceptionTest("StartWithChoiceAndParenthesis", "|(abc)")
    Test.ParseExceptionTest("LexerAlphabetCapital", "abcDxyz")
    Test.ParseExceptionTest("CapitalLetter", "A")
    Test.ParseExceptionTest("Digit", "0abc")
    Test.ParseExceptionTest("HatSymbol", "^abc")
    Test.ParseExceptionTest("Empty", "")
    Test.ParseExceptionTest("ExtraOpeningParenthesis", "((abc)")
    Test.ParseExceptionTest("ExtraClosingParenthesis", "abc)")
    Test.ParseExceptionTest("StartWithChoice", "|abc")
    Test.ParseExceptionTest("StartWithStar", "*abc")
    Test.ParseExceptionTest("EndWithChoice", "abc|")
    Test.ParseExceptionTest("EndWithOpeningParenthesis", "abc(")
    Test.ParseExceptionTest("BadParenthesisOrder", "a)((b)")
    Test.ParseExceptionTest("TooManyClosing", "((((a)))))))")
    Test.ParseExceptionTest("NotEnoughClosing", "(((ab))(")
    Test.ParseExceptionTest("EmptyChoice", "a||b")
    Test.ParseExceptionTest("StartWithClosingParenthesis", ")(")
    Test.ParseExceptionTest("EmptyParenthesis", "ab()")
    Test.ParseExceptionTest("CaretInside", "abc^def")
    Test.ParseExceptionTest("EndWithChoiceAgain", "x|y|z|")
    Test.ParseExceptionTest("EmptyAlternative", "(a|)")

    Test.runAll()

}
