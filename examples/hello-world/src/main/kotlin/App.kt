import vue.*
import welcome.*

interface AppComponent : VueComponent<VData, VProps, VRefs>

object AppOptions : VueOptions<VData, VProps, VRefs, VComputed, AppComponent>(AppComponent::class) {
    override fun VBuilder.render() = welcome()
}

fun VBuilder.app() = child(AppOptions)