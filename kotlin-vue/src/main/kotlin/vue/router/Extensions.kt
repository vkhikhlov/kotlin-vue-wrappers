@file:Suppress("Unused")

package vue.router

import kotlinx.html.Tag
import vue.VBuilder
import vue.vdom.VDOMBuilder

fun <T : Tag> VDOMBuilder<T>.routerView(block: VBuilder.() -> Unit = { }) = child("router-view", block)
fun <T : Tag> VDOMBuilder<T>.routerLink(block: VBuilder.() -> Unit = { }) = child("router-link", block)