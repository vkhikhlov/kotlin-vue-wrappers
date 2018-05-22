@file:Suppress("Unused")

package vue.router

import kotlinx.html.Tag
import vue.VBuilderWithProps
import vue.vdom.VDOMBuilder

fun <T : Tag> VDOMBuilder<T>.routerView(block: VBuilderWithProps<RouterViewProps>.() -> Unit = { }) =
        child("router-view", block)
fun <T : Tag> VDOMBuilder<T>.routerLink(block: VBuilderWithProps<RouterLinkProps>.() -> Unit = { }) =
        child("router-link", block)