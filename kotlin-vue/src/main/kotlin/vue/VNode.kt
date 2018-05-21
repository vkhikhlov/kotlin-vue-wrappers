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
    var slot: String?
}

data class VNodeOptions<T : VProps>(
        val attrs: MutableMap<String, String?> = mutableMapOf(),
        val clazz: MutableMap<String, Boolean> = mutableMapOf(),
        val on: MutableMap<String, (dynamic) -> Unit> = mutableMapOf(),
        val nativeOn: MutableMap<String, (dynamic) -> Unit> = mutableMapOf(),
        var props: T? = undefined,
        var style: CSSStyleDeclaration? = undefined,
        var ref: String? = undefined,
        var key: String? = undefined,
        var slot: String? = undefined
)

interface IVElementDataBuilder<out T : VProps> {
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
    var slot: String?
}

class VElementDataBuilder<T : VProps>(opts: VNodeOptions<T>) : IVElementDataBuilder<T> {
    private val options = opts
    override val bind = object : IVElementDataBuilder.IBind {
        private val clazz = object : IVElementDataBuilder.IBind.IClass {
            override infix fun String.to(that: Boolean) {
                options.clazz[this] = that
            }
        }

        override fun clazz(block: IVElementDataBuilder.IBind.IClass.() -> Unit) {
            clazz.block()
        }

        override fun style(block: CSSStyleDeclaration.() -> Unit) {
            if (options.style == null) options.style = jsObject { }
            options.style?.block()
        }
    }
    override val on = object : IVElementDataBuilder.IOn {
        override fun click(fn: (MouseEvent) -> Unit) {
            options.on["click"] = fn
        }

        override val click = object : IVElementDataBuilder.IOn.IClick {
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

    override var key
        get() = options.key
        set(value) {
            options.key = value
        }

    override var ref: String?
        get() = options.ref
        set(value) {
            options.ref = value
        }

    override var slot: String?
        get() = options.slot
        set(value) {
            options.slot = value
        }
}