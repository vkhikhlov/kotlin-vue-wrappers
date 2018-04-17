import vue.*
import vue.vdom.*
import welcome.*

interface AppComponent : VueComponent<VData, VProps, VOptions, VRefs>

object AppOptions : VueOptions<
        VData,
        VProps,
        VComputed,
        VWatch,
        VOptions,
        VRefs,
        AppComponent>(AppComponent::class) {
    override fun Template.render() {
        root {
            welcome()
        }
    }
}

fun VBuilder.app() = child(AppOptions)