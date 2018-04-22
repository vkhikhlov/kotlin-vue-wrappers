import vue.*
import vue.vdom.*
import welcome.*

interface AppComponent : VueComponent<VData, VProps, VRefs>

object AppOptions : VueOptions<VData, VProps, VRefs, VComputed, AppComponent>(AppComponent::class) {
    override fun Template.render() {
        root {
            welcome()
        }
    }
}

fun VBuilder.app() = child(AppOptions)