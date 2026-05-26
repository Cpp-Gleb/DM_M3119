#include "tree.h"

#include <functional>
#include <sstream>
#include <utility>

Tree::Tree(std::string label) : label_(std::move(label)) {}

Tree::Tree(std::string label, std::vector<Tree> children)
    : label_(std::move(label)), children_(std::move(children)) {}

const std::string& Tree::label() const {
    return label_;
}

const std::vector<Tree>& Tree::children() const {
    return children_;
}

std::string Tree::toDot() const {
    std::ostringstream out;
    int nextId = 0;

    out << "digraph ParseTree {\n";

    auto escape = [](const std::string& text) {
        std::string result;
        for (char ch : text) {
            if (ch == '"') {
                result += "\\\"";
            } else if (ch == '\\') {
                result += "\\\\";
            } else {
                result += ch;
            }
        }
        return result;
    };

    std::function<int(const Tree&)> visit = [&](const Tree& node) {
        const int id = nextId++;
        out << "  n" << id << " [label=\"" << escape(node.label_) << "\"];\n";
        for (const Tree& child : node.children_) {
            const int childId = visit(child);
            out << "  n" << id << " -> n" << childId << ";\n";
        }
        return id;
    };

    visit(*this);
    out << "}\n";
    return out.str();
}

std::string Tree::toString() const {
    if (children_.empty()) {
        return label_;
    }

    std::ostringstream out;
    out << label_ << "(";
    for (std::size_t i = 0; i < children_.size(); ++i) {
        if (i != 0) {
            out << ", ";
        }
        out << children_[i].toString();
    }
    out << ")";
    return out.str();
}

bool Tree::operator==(const Tree& other) const {
    return label_ == other.label_ && children_ == other.children_;
}
