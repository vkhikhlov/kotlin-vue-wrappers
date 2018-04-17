package vue

import org.w3c.dom.HTMLElement
import vue.vdom.Template
import vue.ext.jsObject
import kotlin.reflect.KClass

interface VData
interface VWatch
interface VComputed
interface VProps
interface VOptions
interface VRefs

typealias CreateElement = (renderElement: Any, definition: dynamic, children: Array<Any>?) -> VNode

external interface VueComponent<out D : VData, out P : VProps, out O : VOptions, out R : VRefs> {
    @JsName("\$el")
    val el: HTMLElement

    @JsName("\$parent")
    val parent: Vue<*, *, *, *, *, *, *>

    @JsName("\$data")
    val data: D

    @JsName("\$props")
    val props: P

    @JsName("\$options")
    val options: O

    @JsName("\$refs")
    val refs: R

    @JsName("_isMounted")
    val isMounted: Boolean

    @JsName("\$children")
    val children: Array<Vue<*, *, *, *, *, *, *>>

    @JsName("\$nextTick")
    fun nextTick(callback: () -> Unit, context: Array<Any>?)

    @JsName("\$mount")
    fun mount(elementOrSelector: Any?, hydrating: Boolean? = definedExternally): Vue<*, *, *, *, *, *, *>

    @JsName("\$emit")
    fun emit(event: String, value: Any?)

    @JsName("\$on")
    fun on(event: String, callback: (Any?) -> Unit)

    @JsName("\$once")
    fun once(event: String, callback: (Any?) -> Unit)

    @JsName("\$off")
    fun off(event: String)
}

@Suppress("unused")
abstract class VueOptions<
        out D : VData,
        out P : VProps,
        out C : VComputed,
        out W : VWatch,
        out O : VOptions,
        out R : VRefs,
        out VC : VueComponent<D, P, O, R>>(kClass: KClass<out VC>) {
    @JsName("_extends")
    open fun extends(): VueOptions<*, *, *, *, *, *, *>? = null

    open fun onMounted() = Unit
    open fun onBeforeCreate() = Unit
    open fun getComponentNamePrefix() = ""

    @JsName("__data")
    val data
        get() = that.data

    @JsName("__props")
    val props
        get() = that.props

    // :todo: Make needed requires
    val options get() = that.options
    val refs get() = that.refs
    val el get() = that.el
    val parent get() = that.parent
    val children get() = that.children
    val isMounted get() = that.isMounted
    @Suppress("MemberVisibilityCanBePrivate")
    val that
        get() = _that
    val comp get() = that.unsafeCast<C>()
    private val _data = jsObject<D> { }
    private lateinit var _that: VC

    @Suppress("LeakingThis")
    val name = "${getComponentNamePrefix()}${kClass.simpleName}"

    @Suppress("LeakingThis")
    @JsName("extends")
    private val _extends = extends()

    @JsName("mounted")
    private val mounted = { onMounted() }

    @JsName("beforeCreate")
    private val beforeCreate = { _that = js("this").unsafeCast<VC>(); onBeforeCreate() }

    @Suppress("PrivatePropertyName")
    @JsName("data")
    private val __data = { _data }

    @JsName("computed")
    private val computed = jsObject<C> { }

    @JsName("watch")
    private val watch = jsObject<W> { }

    @Suppress("PrivatePropertyName")
    @JsName("props")
    private val __props = jsObject<P> { }

    @JsName("render")
    val render = { h: CreateElement ->
        Template(h).apply { render() }.build()
    }

    fun data(block: D.() -> Unit = { }) {
        _data.apply(block)
    }

    fun computed(block: C.() -> Unit = { }) {
        computed.apply(block)
    }

    fun watch(block: W.() -> Unit = { }) {
        watch.apply(block)
    }

    fun props(block: P.() -> Unit = { }) {
        __props.apply(block)
    }

    abstract fun Template.render()
    inline operator fun <reified T> (() -> T).unaryPlus() = unsafeCast<T>()
    fun <T> requireIsMounted(f: T): T {
        require(that.isMounted)
        return f
    }
}