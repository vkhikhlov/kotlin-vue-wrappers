package welcome

import vue.*
import vue.vdom.*
import vue.ext.*

interface WelcomeProps : VProps {
    var name: String
}

interface WelcomeComponent : VueComponent<VData, WelcomeProps, VRefs>

object WelcomeOptions : VueOptions<VData, WelcomeProps, VRefs, VComputed, WelcomeComponent>(WelcomeComponent::class) {
    init {
        props {
            name = jsObject { }
        }
    }

    override fun VBuilder.render() = h1 { +"Hello ${props.name}!" }
}

fun VBuilder.welcome(block: VBuilder.() -> Unit = { v.props = jsObject<WelcomeProps> { name = "kotlin-vue" } }) =
        child(WelcomeOptions, block)