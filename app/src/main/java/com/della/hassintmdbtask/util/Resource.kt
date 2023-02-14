package com.della.hassintmdbtask.util


/**
 * This is a sealed class in Kotlin, used to represent the status of a network request in a concise and efficient manner. The class is defined as Resource<T>, where T is the type of data that is being returned as a result of the network request.
 * The class has three subclasses:
 *
 * Success<T>: Represents a successful network request that returns data of type T. It takes the data as a constructor parameter and stores it in the data property.
 *
 * Loading<T>: Represents an ongoing network request that hasn't yet returned data. It takes an optional data parameter of type T that can be used to show a loading state in the UI.
 *
 * Error<T>: Represents a failed network request. It takes a message parameter that represents the error message and an optional data parameter of type T that can be used to show an error state in the UI.
 *
 * By using this class to represent the status of a network request, it becomes easier to handle different scenarios in the UI and to manage the state of the network request in a clear and consistent manner.
 */
sealed class Resource<T>(val data: T? = null, val statusMessage: String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(statusMessage = message)
}