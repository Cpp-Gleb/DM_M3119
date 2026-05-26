cmake_minimum_required(VERSION 3.16)
project(regex_parser LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_library(regex_parser_lib
    src/lexer.cpp
    src/parser.cpp
    src/tree.cpp
)

target_include_directories(regex_parser_lib PUBLIC src)

add_executable(regex_parser src/main.cpp)
target_link_libraries(regex_parser PRIVATE regex_parser_lib)

add_executable(regex_tests test/cpp_tests.cpp)
target_link_libraries(regex_tests PRIVATE regex_parser_lib)
