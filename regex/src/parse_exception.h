#pragma once

#include <cstddef>
#include <stdexcept>
#include <string>

class ParseException : public std::runtime_error {
public:
    ParseException(const std::string& message, std::size_t position)
        : std::runtime_error(message), position_(position) {}

    std::size_t position() const {
        return position_;
    }

private:
    std::size_t position_;
};
