package app.recruit.jetweatherforecast.data

class DataOrException<T, Boolean, E: Exception> ( //using Generics
    var data: T? = null,
    var loading: kotlin.Boolean? = false,
    var e: E? = null
)