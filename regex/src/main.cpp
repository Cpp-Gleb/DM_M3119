#include "parse_exception.h"
#include "parser.h"

#include <fstream>
#include <iostream>

int main(int argc, char* argv[]) {
    if (argc < 2 || argc > 3) {
        std::cerr << "Usage: regex_parser <regex> [tree.dot]\n";
        return 2;
    }

    try {
        Parser parser;
        Tree tree = parser.parse(argv[1]);

        if (argc == 3) {
            std::ofstream out(argv[2]);
            out << tree.toDot();
        } else {
            std::cout << tree.toString() << '\n';
        }
    } catch (const ParseException& e) {
        std::cerr << "Parse error at position " << e.position() << ": " << e.what() << '\n';
        return 1;
    }

    return 0;
}
