package vue

import kotlinx.html.Tag
import vue.ext.*

@DslMarker
annotation class VueDsl

@VueDsl
open class VBuilder(val createElement: CreateElement, val slots: JsonOf<Array<VNode>?>) {
    val children = mutableListOf<Any>()

    fun <P : VProps> create(type: Any, opts: VNodeOptions<P>, children: List<Any>?): VNode {
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
    open val v: VElementDataBuilder<out VProps> = VElementDataBuilder(opts)

    fun child(node: VNode): VNode {
        children.add(node)
        return node
    }

    @Suppress("Unused")
    fun <P : VProps> child(options: VueOptions<*, P, *, *, *>, block: VBuilderWithProps<P>.() -> Unit = { }): VNode {
        val builder = VBuilderWithProps<P>(createElement, slots).apply(block)
        return child(create(options, builder.opts, builder.children))
    }

    @Suppress("Unused")
    fun <P : VProps> child(tagName: String, block: VBuilderWithProps<P>.() -> Unit = { }): Any {
        val builder = VBuilderWithProps<P>(createElement, slots).apply(block)
        return child(create(tagName, builder.opts, builder.children))
    }

    @Suppress("Unused")
    inline fun <reified T : Tag> slot(name: String = "default", crossinline block: VBuilder.() -> Unit) =
            child<VProps>(T::class.simpleName!!) {
                v.slot = name
                block()
            }

    operator fun String.unaryPlus() {
        children.add(this)
    }
}

@Suppress("Unused")
open class VBuilderWithProps<P : VProps>(
        createElement: CreateElement,
        slots: JsonOf<Array<VNode>?>) : VBuilder(createElement, slots) {
    override val v: VElementDataBuilder<P> = VElementDataBuilder(opts.unsafeCast<VNodeOptions<P>>())
}