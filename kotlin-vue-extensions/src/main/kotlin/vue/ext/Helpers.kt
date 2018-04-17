package vue.ext

fun <T : Any> jsObject(builder: T.() -> Unit): T {
    val obj = js("({})") as T
    obj.builder()
    return obj
}

@Suppress("Unused")
interface JsonOf<T>
operator fun <T> JsonOf<T>.get(propertyName: String): T = this.asDynamic()[propertyName] as T
operator fun <T> JsonOf<T>.set(propertyName: String, value: T?) {
    this.asDynamic()[propertyName] = value
}