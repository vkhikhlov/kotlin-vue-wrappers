package vue

import kotlinx.html.Tag
import vue.ext.*

@DslMarker
annotation class VueDsl

@VueDsl
open class VBuilder(val createElement: CreateElement) {
    val children = mutableListOf<Any>()

    fun <T : VProps> create(type: Any, opts: VNodeOptions<T>, children: List<Any>?): Any {
        fun <T> initJsonOf(map: Map<String, T?>): JsonOf<T> {
            val result = jsObject<JsonOf<T>> { }
            map.entries.forEach {
                result[it.key] = it.value
            }
            return result
        }

        val data: VNodeData = jsObject {
            if (opts.attrs.isNotEmpty()) attrs = initJsonOf(opts.attrs)
            if (opts.clazz.isNotEmpty()) clazz = initJsonOf(opts.clazz)
            if (opts.on.isNotEmpty()) on = initJsonOf(opts.on)
            if (opts.nativeOn.isNotEmpty()) nativeOn = initJsonOf(opts.on)
            style = opts.style
            props = opts.props
            ref = opts.ref
            key = opts.key
            slot = opts.slot
        }
        return createElement(type, data, children?.toTypedArray())
    }

    protected val opts = VNodeOptions<VProps>()
    val v = VElementDataBuilder(opts)

    fun child(node: Any): Any {
        children.add(node)
        return node
    }

    @Suppress("Unused")
    fun child(options: VueOptions<*, *, *, *, *>, block: VBuilder.() -> Unit = { }): Any {
        val builder = VBuilder(createElement).apply(block)
        return child(create(options, builder.opts, builder.children))
    }

    @Suppress("Unused")
    fun child(tagName: String, block: VBuilder.() -> Unit = { }): Any {
        val builder = VBuilder(createElement).apply(block)
        return child(create(tagName, builder.opts, builder.children))
    }

    @Suppress("Unused")
    inline fun <reified T : Tag> slot(name: String = "default", crossinline block: VBuilder.() -> Unit) =
            child(T::class.simpleName!!) {
                v.slot = name
                block()
            }

    operator fun String.unaryPlus() {
        children.add(this)
    }
}