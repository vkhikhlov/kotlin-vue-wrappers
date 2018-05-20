package vue.vdom

import kotlinx.html.*
import org.w3c.dom.events.Event
import vue.CreateElement
import vue.VBuilder

open class VDOMBuilder<out T : Tag>(createElement: CreateElement, factory: (TagConsumer<Unit>) -> T) : VBuilder(createElement) {
    open fun build() = create(attrs.tagName, opts, children)

    private val consumer = object : TagConsumer<Unit> {
        override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
            opts.attrs[attribute] = value
        }

        override fun onTagComment(content: CharSequence) {}
        override fun onTagContent(content: CharSequence) {}
        override fun onTagContentEntity(entity: Entities) {}
        override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {}
        override fun onTagStart(tag: Tag) {}
        override fun onTagEnd(tag: Tag) {}
        override fun onTagEvent(tag: Tag, event: String, value: (Event) -> Unit) {}
        override fun finalize() {}
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val attrs: T = factory(consumer)

    init {
        attrs.attributesEntries.forEach {
            if (it.value.isNotEmpty()) {
                opts.attrs[it.key] = it.value
                if (it.key == "class") v.bind.clazz { it.value to true }
            }
        }
    }
}