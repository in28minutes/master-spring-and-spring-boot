# 99 - Hafeez Module Additions (51-junit)

**Module:** `master-spring-and-spring-boot/51-junit`

JUnit: **Jupiter 6.0.3** (Feb 2026). Java: **17+** (required by JUnit 6).

---

## 1. What changed, file by file

### New files

| File | Why |
|---|---|
| `pom.xml` | Module had no build file, so the tests could not run from the CLI. This pom uses the existing `src/` and `test/` folders (no files moved), JUnit 6 via the official BOM, and Surefire 3.5.4. `mvn test` now works. |
| `test/.../ModernJUnit5FeaturesTest.java` | Short tour of the Jupiter features the "5 steps" lecture does not cover: `@DisplayName`, `@ParameterizedTest` (`@ValueSource`, `@CsvSource`), `assertThrows`, `assertTimeout`, `assumeTrue`, `@RepeatedTest`, `@Disabled`, `@Nested`, `@Tag`. One small test per annotation. |
| `99-hafeez-module-additions.md` | This file. |

### Modified files

| File | What changed | Why |
|---|---|---|
| `src/.../MyMath.java` | Added `divide(int, int)` (throws `ArithmeticException` on `/0`) and `isEven(int)`. Added a Javadoc and an `Objects.requireNonNull` check on `calculateSum`. | Gives the new tests something to use `assertThrows` and `@ParameterizedTest` on. `calculateSum` behaviour unchanged for non-null inputs. |
| `test/.../MyMathTest.java` | Kept the original two tests. Added three: `calculateSum_grouped` (`assertAll`), `divide_byZero_throws` (`assertThrows` + message check), `divide_happyPath`. | Shows the original sum tests next to the patterns used in real code. |
| `test/.../MyAssertTest.java` | Replaced the JUnit 4 import `org.junit.Assert.assertArrayEquals` with the Jupiter version. Added real `assertNotNull` / `assertNull` lines (were `// comments` before). Flipped the failing `assertArrayEquals({1,2}, {2,1})` to a passing one and noted in a comment that the original was an in-lecture failure demo. | The JUnit 4 import was a real bug, it only compiled because JUnit 4 was on the classpath. Would have blocked `mvn test` once the pom existed. |
| `readme.md` | Kept the "Step 1-5" lecture text and code listings. Added: **How to Run**, **Notes** (JUnit 4 vs 5, assertions cheat sheet, lifecycle table, style tips), **Useful References** doc links. | Doc links. |

---

## 2. Before / after

| | Before | After |
|---|---|---|
| Tests | 6 | 34 (1 intentionally skipped) |
| Jupiter features covered | `@Test`, lifecycle, basic asserts | + `@DisplayName`, `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, `assertThrows`, `assertAll`, `assertTimeout`, `assumeTrue`, `@RepeatedTest`, `@Disabled`, `@Nested`, `@Tag` |
| Build file | none (Eclipse only) | `pom.xml` - `mvn test` works |
| JUnit version | mixed JUnit 4 import + Jupiter | Jupiter only, pinned to JUnit 6.0.3 via BOM |
| `mvn test` | not possible (no pom) | BUILD SUCCESS, 34 tests, 1 skipped, 0 failures |

---

## 3. How to verify

```bash
cd master-spring-and-spring-boot/51-junit
mvn test
```

Expected: `Tests run: 34, Failures: 0, Errors: 0, Skipped: 1` then `BUILD SUCCESS`.

---
