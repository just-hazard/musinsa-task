package exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFoundException(exception: EntityNotFoundException): ResponseEntity<Any> {
        return ResponseEntity<Any>(exception.message?.let { ApiError(it) }, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}