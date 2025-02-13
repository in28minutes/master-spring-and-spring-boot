/**
 * A Kotlin `data class` that represents structured error details for exception handling.
 *
 * ## Features:
 * - **Encapsulates error details** in a structured format.
 * - **Kotlin `data class` benefits**:
 *   - Auto-generates `toString()`, `equals()`, `hashCode()`, and `copy()`.
 *   - Immutable properties (`val`) ensure thread safety.
 * - **LocalDateTime for timestamping** the exact moment of the error.
 *
 * @param timestamp The exact time the error occurred.
 * @param message A brief description of the error.
 * @param details Additional details, such as the request path.
 */
package com.in28minutes.rest.webservices.restfulwebservices.exception

import java.time.LocalDateTime

data class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String,
    val details: String
)