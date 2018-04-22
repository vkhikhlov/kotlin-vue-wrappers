import vue.*
import vue.vdom.*

interface MainComponent : VueComponent<VData, VProps, VRefs>

@Suppress("Unused")
fun main(args: Array<String>) {
    val vm = Vue(object : VueOptions<VData, VProps, VRefs, VComputed, MainComponent>(MainComponent::class) {
        override fun Template.render() {
            root {
                app()
            }
        }
    })
    vm.mount("#app")
}