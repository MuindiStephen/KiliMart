package com.steve_md.joomia.util

import kotlinx.coroutines.flow.*


/*An algorithm that provides an easy function
 * to fetch resource from both database ana network
 */

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    //First step, fetch data from the local cache
    val data = query().first()

    //If shouldFetch returns true,
    val resource = if (shouldFetch(data)) {

        //Dispatch a message to the UI that you're doing some background work
        emit(ApiStates.Loading(data))

        try {

            //make a networking call
            val resultType = fetch()

            //save it to the database
            saveFetchResult(resultType)

            //Now fetch data again from the database and Dispatch it to the UI
            query().map { ApiStates.Success(it) }

        } catch (throwable: Throwable) {

            //Dispatch any error emitted to the UI, plus data emitted from the Database
            query().map { ApiStates.Error(throwable,it) }

        }

        //If should fetch returned false
    } else {
        //Make a query to the database and Dispatch it to the UI.
        query().map { ApiStates.Success(it) }
    }

    //Emit the resource variable
    emitAll(resource)
}