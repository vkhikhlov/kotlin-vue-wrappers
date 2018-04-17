import vue.*
import vue.vdom.*

interface MainComponent : VueComponent<VData, VProps, VOptions, VRefs>

@Suppress("Unused")
fun main(args: Array<String>) {
    val vm = Vue(object : VueOptions<
            VData,
            VProps,
            VComputed,
            VWatch,
            VOptions,
            VRefs,
            VueComponent<VData, VProps, VOptions, VRefs>>(MainComponent::class) {
        override fun Template.render() {
            root {
                app()
            }
        }
    })
    vm.mount("#app")
}