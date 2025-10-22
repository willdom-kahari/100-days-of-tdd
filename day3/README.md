# **Day**: 3

- **Title**: Introduction to AssertJ for Fluent Assertions
- **Time Estimate (hours)**: 2.5
- **Learning Objectives**: Integrate AssertJ into the project; replace standard JUnit assertions with fluent AssertJ
  assertions.
- **Daily Tasks**:
    1. Add the `assertj-core` dependency to your Gradle project.
    2. Refactor all existing JUnit assertions to use AssertJ's fluent API (e.g., `assertThat(result).isEqualTo(3)`).
    3. Explore advanced AssertJ features like collection assertions (`hasSize`, `containsExactly`).
    4. Write a new test for a `List<String> getNames()` method using AssertJ's collection assertions.
- **Kata**: **String Calculator Kata (Step 3)**. Make the `add` method handle an unknown amount of numbers. Write a test
  with an array of inputs and use AssertJ to verify the result.
- **Expected Outputs**: A test suite that exclusively uses AssertJ for all assertions, demonstrating improved
  readability.
- **Verification Citations**: AssertJ is a widely adopted library that provides a rich and fluent set of assertions for
  Java tests . Its latest stable version is actively maintained .
