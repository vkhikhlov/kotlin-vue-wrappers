package vue

import org.w3c.dom.HTMLElement
import vue.ext.JsonOf
import vue.ext.jsObject
import vue.router.Router
import kotlin.reflect.KClass

external interface VData
external interface VWatch
external interface VComputed
external interface VProps
external interface VRefs

typealias CreateElement = (renderElement: Any, definition: dynamic, children: Array<Any>?) -> VNode

external interface VueComponent<out D : VData, out P : VProps, out R : VRefs> {
    @JsName("\$el")
    val el: HTMLElement

    @JsName("\$parent")
    val parent: VueComponent<*, *, *>

    @JsName("\$data")
    val data: D

    @JsName("\$props")
    val props: P

    @JsName("\$refs")
    val refs: R

    @JsName("\$router")
    val router: Router

    @JsName("\$slots")
    val slots: JsonOf<Array<VNode>?>

    @JsName("_isMounted")
    val isMounted: Boolean

    @JsName("\$children")
    val children: Array<VueComponent<*, *, *>>

    @JsName("\$nextTick")
    fun nextTick(callback: () -> Unit, context: Array<Any>?)

    @JsName("\$mount")
    fun mount(elementOrSelector: Any?, hydrating: Boolean? = definedExternally): VueComponent<*, *, *>

    @JsName("\$emit")
    fun emit(event: String, value: Any?)

    @JsName("\$on")
    fun on(event: String, callback: (Any?) -> Unit)

    @JsName("\$once")
    fun once(event: String, callback: (Any?) -> Unit)

    @JsName("\$off")
    fun off(event: String)
}

@Suppress("Unused")
abstract class VueOptions<
        out D : VData,
        out P : VProps,
        out R : VRefs,
        out C : VComputed,
        out VC : VueComponent<D, P, R>>(kClass: KClass<out VC>) {
    @JsName("_extends")
    open fun extends(): VueOptions<*, *, *, *, *>? = null

    open fun onCreated() = Unit
    open fun onMounted() = Unit
    open fun onBeforeCreate() = Unit
    open fun onBeforeMount() = Unit
    open fun onBeforeDestroy() = Unit
    open fun onDestroyed() = Unit
    open fun onBeforeUpdate() = Unit
    open fun onUpdated() = Unit

    open fun getComponentNamePrefix() = ""

    @JsName("__data")
    val data
        get() = requireIsCreated(that.data)
    @JsName("__props")
    val props
        get() = requireIsCreated(that.props)

    val refs get() = requireIsMounted(that.refs)
    val el get() = requireIsMounted(that.el)
    val parent get() = requireIsCreated(that.parent)
    val children get() = requireIsCreated(that.children)
    val isMounted get() = that.isMounted
    val slots get() = that.slots
    var isCreated = false
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

    @JsName("created")
    private val created = { isCreated = true; onCreated() }

    @JsName("updated")
    private val updated = { onUpdated() }

    @JsName("destroyed")
    private val destroyed = { onDestroyed() }

    @JsName("beforeMount")
    private val beforeMount = { onBeforeMount() }

    @JsName("beforeCreate")
    private val beforeCreate = { _that = js("this").unsafeCast<VC>(); onBeforeCreate() }

    @JsName("beforeUpdate")
    private val beforeUpdate = { onBeforeUpdate() }

    @JsName("beforeDestroy")
    private val beforeDestroy = { onBeforeDestroy() }

    @Suppress("PrivatePropertyName")
    @JsName("data")
    private val __data = { _data }

    @JsName("computed")
    private val computed = jsObject<C> { }

    @JsName("watch")
    private var watch: dynamic = undefined

    @Suppress("PrivatePropertyName")
    @JsName("props")
    private val __props = jsObject<P> { }

    @JsName("render")
    val render = { h: CreateElement -> VBuilder(h, that.slots).render() }

    @JsName("router")
    var router: Router? = undefined

    fun data(block: D.() -> Unit = { }) {
        _data.apply(block)
    }

    fun computed(block: C.() -> Unit = { }) {
        computed.apply(block)
    }

    fun <W : VWatch> watch(block: W.() -> Unit = { }) {
        watch = jsObject(block)
    }

    fun props(block: P.() -> Unit = { }) {
        __props.apply(block)
    }

    abstract fun VBuilder.render(): VNode
    inline operator fun <reified T> (() -> T).unaryPlus() = unsafeCast<T>()
    private fun <T> requireIsMounted(value: T): T {
        require(that.isMounted)
        return value
    }

    private fun <T> requireIsCreated(value: T): T {
        require(isCreated)
        return value
    }
}