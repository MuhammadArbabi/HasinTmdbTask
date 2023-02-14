package com.della.hassintmdbtask.util

/**
 * This is an extension function in Kotlin that extends the String class. The function takes a String object and returns a new String object that is a URL pointing to a movie poster image.

The function uses string template syntax to create a new string by concatenating the "https://image.tmdb.org/t/p/w342" string with the input String object (referred to as this within the extension function). The resulting string is a URL for a movie poster image of size 342 pixels in width.

This function can be used to easily convert a movie poster path from the TMDB API into a full URL for the poster image. For example, given a movie poster path of "path/to/poster", calling toPosterUrl() on that path would return the full URL "https://image.tmdb.org/t/p/w342/path/to/poster".
 */
fun String.toPosterUrl() = "https://image.tmdb.org/t/p/w342$this"


/**
 * This is an extension function in Kotlin that extends the String class. The function takes a String object and returns a new String object that is a URL pointing to a movie backdrop image.

The function uses string template syntax to create a new string by concatenating the "https://image.tmdb.org/t/p/original" string with the input String object (referred to as this within the extension function). The resulting string is a URL for a movie backdrop image of original size.

This function can be used to easily convert a movie backdrop path from the TMDB API into a full URL for the backdrop image. For example, given a movie backdrop path of "path/to/backdrop", calling toBackdropUrl() on that path would return the full URL "https://image.tmdb.org/t/p/original/path/to/backdrop".
 */
fun String.toBackdropUrl() = "https://image.tmdb.org/t/p/original$this"