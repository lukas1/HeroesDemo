package com.vyletel.fparch.core

/**
 * Created by lukas on 05/01/2018.
 */
sealed class Either<Error, out Result> {

    inline fun fold(
            crossinline failCase: (Error) -> Unit,
            crossinline successCase: (Result) -> Unit
    ) {
        when (this) {
            is Fail -> failCase(error)
            is Success -> successCase(result)

        }
    }

    inline fun <B>map(crossinline block: (Result) -> B): Either<Error, B> = when (this) {
        is Fail -> Fail(error)
        is Success -> Success(block(result))
    }

    class Fail<Error, out Result>(val error: Error) : Either<Error, Result>()

    class Success<Error, out Result>(val result: Result) : Either<Error, Result>()
}