package util

fun checkNotNullOrEmpty(value: String?): String {
    if (value.isNullOrEmpty()) {
        throw IllegalStateException()
    } else return value
}