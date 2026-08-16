# Testing Examples

This directory demonstrates testing rules from the handbook:

- Arrange, Act, Assert comments.
- One behaviour per test.
- Temporary directories instead of developer-specific file paths.
- Real filesystem verification when the behaviour is file output.
- Checked exception wrapping for file failures.

The example intentionally writes a real temporary file because mocking the file write would not verify the behaviour that matters.
