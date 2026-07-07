---
name: kotlin patterns
description: Conventions for using Kotlin
---

## 1. Remove usage of `!!` operator

The `!!` operator should be avoided in Kotlin as it can lead to `NullPointerException` at runtime. Instead, use safe calls (`?.`) or the `let` scope function to handle nullable values more gracefully.

## 2. Remove the safe call operator (`?.`) when possible

Using the safe call operator (`?.`) is a good practice, but it should be used judiciously.
It makes no sense to use this operator when the value is guaranteed to be non-null, as it can lead to unnecessary null checks and reduce code readability.

## 3. Avoid using `var` when possible

In Kotlin, it is recommended to use `val` instead of `var` whenever possible. This helps to make the code more readable and maintainable, as it makes it clear that the value of a variable is not expected to change.
