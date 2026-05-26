#pragma once

#include <string>
#include <vector>

class Tree {
public:
    explicit Tree(std::string label);
    Tree(std::string label, std::vector<Tree> children);

    const std::string& label() const;
    const std::vector<Tree>& children() const;

    std::string toDot() const;
    std::string toString() const;
    bool operator==(const Tree& other) const;

private:
    std::string label_;
    std::vector<Tree> children_;
};
