package vue

import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import vue.ext.*

external interface VNode

external interface VNodeData {
    @JsName("class")
    var clazz: JsonOf<Boolean>
    var style: CSSStyleDeclaration?
    var attrs: JsonOf<String>
    var props: Any?
    var on: JsonOf<(Event) -> Unit>
    var nativeOn: JsonOf<(Event) -> Unit>
    var ref: String?
    var key: String?
}

data class VNodeDataOptions<T : VProps>(
        val attrs: MutableMap<String, String?> = mutableMapOf(),
        val clazz: MutableMap<String, Boolean> = mutableMapOf(),
        val on: MutableMap<String, (dynamic) -> Unit> = mutableMapOf(),
        val nativeOn: MutableMap<String, (dynamic) -> Unit> = mutableMapOf(),
        var props: T? = null,
        var style: CSSStyleDeclaration? = null,
        var ref: String? = null,
        var key: String? = null
)

interface IV<out T : VProps> {
    interface IBind {
        interface IClass {
            infix fun String.to(that: Boolean)
        }

        fun style(block: CSSStyleDeclaration.() -> Unit)
        fun clazz(block: IClass.() -> Unit)
    }

    interface IOn {
        interface IClick {
            fun ctrl(fn: (MouseEvent) -> Unit)
        }

        fun click(fn: (MouseEvent) -> Unit)
        val click: IClick
        operator fun <T : Event> invoke(eventName: String, fn: (T) -> Unit)
    }

    val bind: IBind
    val on: IOn
    val nativeOn: IOn
    val props: T?
    val attrs: MutableMap<String, String?>
    var ref: String?
    var key: String?
}

class V<T : VProps>(opts: VNodeDataOptions<T>) : IV<T> {
    private val options = opts
    override val bind = object : IV.IBind {
        private val clazz = object : IV.IBind.IClass {
            override infix fun String.to(that: Boolean) {
                options.clazz[this] = that
            }
        }

        override fun clazz(block: IV.IBind.IClass.() -> Unit) {
            clazz.block()
        }

        override fun style(block: CSSStyleDeclaration.() -> Unit) {
            if (options.style == null) options.style = jsObject { }
            options.style?.block()
        }
    }
    override val on = object : IV.IOn {
        override fun click(fn: (MouseEvent) -> Unit) {
            options.on["click"] = fn
        }

        override val click = object : IV.IOn.IClick {
            override fun ctrl(fn: (MouseEvent) -> Unit) {
                options.on["click"] = {
                    if ((it as MouseEvent).ctrlKey) fn(it)
                }
            }
        }

        override fun <T : Event> invoke(eventName: String, fn: (T) -> Unit) {
            options.on[eventName] = fn
        }
    }
    override val nativeOn = on

    override var props
        get() = options.props
        set(value) {
            options.props = value
        }

    override var attrs
        get() = options.attrs
        set(value) {
            options.attrs.putAll(value)
        }

    override var key: String? = null
        set(value) {
            options.key = value
        }

    override var ref: String? = null
        set(value) {
            options.ref = value
        }
}