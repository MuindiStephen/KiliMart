package com.steve_md.joomia.util



// Wraps all Api responses
sealed class ApiStates<T>
     (val data: T? = null, val error: Throwable? = null)
{
     class Success<T>(data: T?) : ApiStates<T>(data)
     class Loading<T>(data: T? = null) : ApiStates<T>(data)
     class Error<T>(throwable: Throwable?,data: T? = null) : ApiStates<T>(data, throwable)
}
