package com.steve_md.joomia.util

sealed class ApiStates<T>(val data: T? = null, val message: String? = null) {
     class Success<T>(data: T?) : ApiStates<T>(data)
     class Loading<T>(data: T? = null) : ApiStates<T>(data)
     class Error<T>(message: String?,  data: T? = null) : ApiStates<T>(data, message)
}
