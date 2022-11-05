package tech.mrgreener.application

open class MrGreenException(
    val error_message: String,
    val error_code: Int = 500
) : RuntimeException(error_message)


open class PermissionDeniedException(error_message: String) :
    MrGreenException(error_message = error_message, error_code = 403)

open class NotFoundException(error_message: String) :
    MrGreenException(error_message = error_message, error_code = 404)