# Day: 1
- **Title**: TDD Mindset & JUnit 5 Fundamentals
- **Time Estimate (hours)**: 2.5
- **Learning Objectives**: Understand the Red-Green-Refactor cycle; set up a Gradle project with JUnit 5; write a first failing test.
- **Daily Tasks**:
    1. Read about the core principles of TDD (Red-Green-Refactor) from a trusted source.
    2. Create a new Gradle project (e.g., `tdd-journey`) and add JUnit 5 dependencies (`junit-jupiter`).
    3. Write a failing test for a simple `Calculator.add(int a, int b)` method that does not yet exist.
    4. Implement the minimal code to make the test pass.
    5. Refactor the code (if needed) and observe the test still passes.
- **Kata**: **String Calculator Kata (Step 1)**. Write a method `add(String numbers)` that returns 0 for an empty string. Start with a failing test. [Source: Coding Dojo](https://codingdojo.org/kata/StringCalculator/)
- **Expected Outputs**: A Gradle project with a passing `CalculatorTest` and the first test for the String Calculator.
- **Verification Citations**: JUnit 5 is the current standard for Java testing on the JVM . The `junit-jupiter` artifact is the standard for writing JUnit 5 tests .
