# **Day**: 2

- **Title**: JUnit 5 Assertions & Test Structure
- **Time Estimate (hours)**: 2.5
- **Learning Objectives**: Master JUnit 5's assertion API; learn to structure tests with `@BeforeEach` and `@AfterEach`.
- **Daily Tasks**:
    1. Study JUnit 5's core assertion methods (`assertEquals`, `assertTrue`, `assertThrows`, etc.).
    2. Refactor yesterday's tests to use more descriptive assertion messages.
    3. Add tests for `Calculator.subtract`, `multiply`, and `divide` (handle division by zero with `assertThrows`).
    4. Use `@BeforeEach` to set up a fresh `Calculator` instance for each test.
- **Kata**: **String Calculator Kata (Step 2)**. Extend `add` to handle a single number (e.g., "1" returns 1) and two
  numbers (e.g., "1,2" returns 3). Write a new failing test for each scenario before implementation.
- **Expected Outputs**: A `CalculatorTest` class with multiple test methods using various assertions and lifecycle
  callbacks.
- **Verification Citations**: The official JUnit 5 User Guide provides comprehensive documentation on assertions and
  lifecycle callbacks.
