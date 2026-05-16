:- begin_tests(gf).

:- use_module(gf).

gf_normalize(Polynomial, Normalized) :-
    reverse(Polynomial, Reversed),
    gf_drop_zeroes(Reversed, TrimmedReversed),
    reverse(TrimmedReversed, Normalized).

gf_drop_zeroes([Coeff | Rest], Trimmed) :-
    number(Coeff),
    Coeff =:= 0,
    !,
    gf_drop_zeroes(Rest, Trimmed).
gf_drop_zeroes(Polynomial, Polynomial).

assert_gf_equal(Expected, Actual) :-
    gf_normalize(Expected, NormalizedExpected),
    gf_normalize(Actual, NormalizedActual),
    assertion(NormalizedActual == NormalizedExpected).

test(coeff_existing) :-
    gf_coeff([3, 0, 8, 1], 2, Coeff),
    assertion(Coeff =:= 8).

test(coeff_missing) :-
    gf_coeff([3, 0, 8, 1], 10, Coeff),
    assertion(Coeff =:= 0).

test(coeff_zero_degree) :-
    gf_coeff([42, 7, 9], 0, Coeff),
    assertion(Coeff =:= 42).

test(add_different_lengths) :-
    gf_add([1, 2, 3], [10, 20], Result),
    assert_gf_equal([11, 22, 3], Result).

test(add_with_negative_coefficients) :-
    gf_add([5, -2, 0, 4], [-5, 3, 7], Result),
    assert_gf_equal([0, 1, 7, 4], Result).

test(scalar_mul_positive) :-
    gf_scalar_mul(3, [1, 2, 3], Result),
    assert_gf_equal([3, 6, 9], Result).

test(scalar_mul_zero) :-
    gf_scalar_mul(0, [5, -2, 1], Result),
    assert_gf_equal([0, 0, 0], Result).

test(shift_zero) :-
    gf_shift([1, 2, 3], 0, Result),
    assert_gf_equal([1, 2, 3], Result).

test(shift_three) :-
    gf_shift([4, 5], 3, Result),
    assert_gf_equal([0, 0, 0, 4, 5], Result).

test(mul_simple) :-
    gf_mul([1, 1], [1, 1, 1], Result),
    assert_gf_equal([1, 2, 2, 1], Result).

test(mul_with_gap_coefficients) :-
    gf_mul([1, 0, 2], [0, 3], Result),
    assert_gf_equal([0, 3, 0, 6], Result).

test(mul_larger_polynomials) :-
    gf_mul([2, 1, 3], [1, 4], Result),
    assert_gf_equal([2, 9, 7, 12], Result).

test(prefix_sums_positive) :-
    gf_prefix_sums([1, 2, 3, 4], Result),
    assertion(Result == [1, 3, 6, 10]).

test(prefix_sums_mixed_signs) :-
    gf_prefix_sums([-1, 3, -2, 5], Result),
    assertion(Result == [-1, 2, 0, 5]).

test(range_from_zero) :-
    gf_range(0, 3, Result),
    assert_gf_equal([1, 1, 1, 1], Result).

test(range_shifted) :-
    gf_range(2, 5, Result),
    assert_gf_equal([0, 0, 1, 1, 1, 1], Result).

test(count_sum_three_bounded_variables) :-
    gf_count_sum([0-2, 0-2, 0-2], 3, Count),
    assertion(Count =:= 7).

test(count_sum_mixed_bounds) :-
    gf_count_sum([2-10, 3-7, 0-5], 14, Count),
    assertion(Count =:= 30).

test(count_sum_fixed_variables) :-
    gf_count_sum([1-1, 2-2, 3-3], 6, Count),
    assertion(Count =:= 1),
    gf_count_sum([1-1, 2-2, 3-3], 7, ImpossibleCount),
    assertion(ImpossibleCount =:= 0).

test(catalan_values) :-
    catalan(0, C0),
    assertion(C0 =:= 1),
    catalan(3, C3),
    assertion(C3 =:= 5),
    catalan(6, C6),
    assertion(C6 =:= 132),
    catalan(7, C7),
    assertion(C7 =:= 429).

:- end_tests(gf).
