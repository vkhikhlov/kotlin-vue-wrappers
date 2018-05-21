package vue

import org.w3c.dom.HTMLElement
import vue.ext.JsonOf
import vue.router.Router

@Suppress("Unused")
@JsModule("vue")
open external class Vue<
        D : VData,
        P : VProps,
        R : VRefs,
        C : VComputed,
        VC: VueComponent<D, P, R>
        >(options: VueOptions<D, P, R, C, VC> = definedExternally) : VueComponent<D, P, R> {
    override val props: P
    override val data: D
    override val refs: R
    override val router: Router
    override val slots: JsonOf<Array<VNode>?>
    override val isMounted: Boolean
    override val parent: VueComponent<*, *, *>
    override val children: Array<VueComponent<*, *, *>>
    override val el: HTMLElement
    override fun nextTick(callback: () -> Unit, context: Array<Any>?)
    override fun mount(elementOrSelector: Any?, hydrating: Boolean?): VueComponent<*, *, *>
    override fun emit(event: String, value: Any?)
    override fun off(event: String)
    override fun on(event: String, callback: (Any?) -> Unit)
    override fun once(event: String, callback: (Any?) -> Unit)

    companion object {
        fun extend(options: VueOptions<*, *, *, *, *>?): VueComponent<*, *, *>
        fun component(id: String, options: VueOptions<*, *, *, *, *>): VueComponent<*, *, *>
        fun <T> use(plugin: dynamic, options: T? = definedExternally)
    }
}