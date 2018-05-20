@file:JsModule("vue-router")
@file:Suppress("Unused")

package vue.router

import vue.VProps
import vue.VueOptions

@JsName("default")
external object VueRouter

@JsName("default")
open external class Router(options: RouterOptions?)

external interface RouterOptions {
    var routes: Array<RouterConfig>
    var mode: String
}

external interface RouterConfig {
    var path: String
    var component: VueOptions<*, *, *, *, *>
}

external interface RouterLinkProps : VProps {
    var to: String
    var activeClass: String
}

external interface RouterViewProps : VProps {
    var name: String
}