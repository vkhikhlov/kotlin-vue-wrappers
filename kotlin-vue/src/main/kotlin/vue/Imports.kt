package vue

import org.w3c.dom.HTMLElement

@JsModule("vue")
open external class Vue<
        out D : VData,
        out P : VProps,
        C : VComputed,
        W : VWatch,
        out O : VOptions,
        out R : VRefs,
        out VC : VueComponent<D, P, O, R>
        >(options: VueOptions<D, P, C, W, O, R, VC> = definedExternally) : VueComponent<D, P, O, R> {
    override val props: P
    override val data: D
    override val options: O
    override val refs: R
    override val isMounted: Boolean
    override val parent: Vue<*, *, *, *, *, *, *>
    override val children: Array<Vue<*, *, *, *, *, *, *>>
    override val el: HTMLElement
    override fun nextTick(callback: () -> Unit, context: Array<Any>?)
    override fun mount(elementOrSelector: Any?, hydrating: Boolean?): Vue<*, *, *, *, *, *, *>
    override fun emit(event: String, value: Any?)
    override fun off(event: String)
    override fun on(event: String, callback: (Any?) -> Unit)
    override fun once(event: String, callback: (Any?) -> Unit)

    companion object {
        fun extend(options: VueOptions<*, *, *, *, *, *, *>?): Vue<*, *, *, *, *, *, *>
        fun component(id: String, options: VueOptions<*, *, *, *, *, *, *>): Vue<*, *, *, *, *, *, *>
        fun <T> use(plugin: dynamic, options: T? = definedExternally)
    }
}