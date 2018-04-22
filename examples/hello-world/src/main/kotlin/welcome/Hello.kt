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

    override fun Template.render() {
        root {
            keep = false
            h1 { +"Hello ${props.name}!" }
        }
    }
}

fun VBuilder.welcome(props: WelcomeProps.() -> Unit = { name = "kotlin-vue" }) = child(WelcomeOptions, props)