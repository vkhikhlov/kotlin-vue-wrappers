package vue

import vue.ext.*

@DslMarker
annotation class VueDsl

@VueDsl
open class VBuilder(val createElement: CreateElement) {
    val children = mutableListOf<Any>()

    fun <T : VProps> create(type: Any, opts: VNodeDataOptions<T>, children: List<Any>?): Any {
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
        }
        return createElement(type, data, children?.toTypedArray())
    }

    protected val opts = VNodeDataOptions<VProps>()
    val v = V(opts)

    fun child(node: Any): Any {
        children.add(node)
        return node
    }

    fun child(type: Any, opts: VNodeDataOptions<VProps> = VNodeDataOptions(), children: List<Any>? = null) =
            child(create(type, opts, children))

    fun <P : VProps> child(type: VueOptions<*, P, *, *, *>, props: P.() -> Unit = { }): Any {
        val opts = VNodeDataOptions<P>()
        opts.props = jsObject(props)
        return child(create(type, opts, null))
    }

    fun <T : VProps> child(type: Any, v: V<T>.() -> Unit = { }): Any {
        val opts = VNodeDataOptions<T>()
        val vOpts = V(opts)
        vOpts.apply(v)
        return child(create(type, opts, null))
    }

    operator fun String.unaryPlus() {
        children.add(this)
    }
}