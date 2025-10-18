package saritoga.crud1.helper

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//rest controller sakti, lgsg dilempar kesini
@RestControllerAdvice
class GlobalController {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(error: NoSuchElementException): ResponseEntity<Any> = ResponseEntity.status(404).body(mapOf("error" to error.message))

    @ExceptionHandler(SecurityException::class)
    fun handleSecurity(error: SecurityException): ResponseEntity<Any> = ResponseEntity.status(401).body(mapOf("error" to error.message))

    @ExceptionHandler(EmptyDataException::class)
    fun handleEmptyData(ex: EmptyDataException): ResponseEntity<Any> {
        val body = mapOf(
            "info" to ex.message
        )
        return ResponseEntity.status(200).body(body) // ✅ bukan error
    }
}