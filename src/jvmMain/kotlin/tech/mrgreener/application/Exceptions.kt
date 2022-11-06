package tech.mrgreener.application

open class MrGreenerException(
    val error_message: String,
    val error_code: Int = 500
) : RuntimeException(error_message)


open class PermissionDeniedException(error_message: String) :
    MrGreenerException(error_message = error_message, error_code = 403)

open class NotFoundException(error_message: String) :
    MrGreenerException(error_message = error_message, error_code = 404)


open class BadRequestException(error_message: String) :
    MrGreenerException(error_message = error_message, error_code = 400)