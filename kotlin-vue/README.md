# kotlin-vue ![Download](https://api.bintray.com/packages/vkhikhlov/kotlin-vue-wrappers/kotlin-vue/images/download.svg)
Kotlin wrapper for Vue library.

### Installation

All artifacts are published on [Bintray](https://bintray.com/vkhikhlov/kotlin-vue-wrappers/kotlin-vue)

#### Gradle example

Add repository:

```groovy
maven {
    url "https://dl.bintray.com/vkhikhlov/kotlin-vue-wrappers"
}
```

Add dependency:

```groovy
compile "com.github.vkhikhlov:kotlin-vue:0.0.2"
```

### Create Vue component

In most cases Vue recommends using templates to build your HTML. But also Vue has render functions, 
a closer-to-the-compiler alternative to templates.

Let's give an examples. Suppose we have next Vue component located in Hello.vue file:

```vue
<template>
    <h1>
        Hello, {{ props.name }}!
    </h1>
</template>

<script>
    export default {
        props: {
            name: {
                type: String,
                default: "kotlin-vue"
            }
        }
    };
</script>
```

then in the same directory we can use this component like that:

```vue
<template>
    <div>
        <Hello></Hello>
    </div>
</template>

<script>
    import Hello from './Hello'
    export default {
        components: {
            Hello
        }
    };
</script>
```

If we rewrite this example to use render functions, it will look like that:

```vue
<script>
    export default {
        props: {
            name: {
                type: String,
                default: "kotlin-vue"
            }
        },
        render(h) {
            return h('h1', [`Hello ${this.name}!`]);
        }
    };
</script>
```

and then:

```vue
<script>
    import Hello from './Hello'
    export default {
        render(h) {
            return h('div', [h(Hello)]);
        }
    };
</script>
```

But refer to official Vue documentation, if you’re writing a lot of render functions, it might feel painful to use them.
That’s why there’s a Babel plugin to use JSX with Vue, getting us back to a syntax that’s closer to 
templates. So, using JSX we can rewrite it like that:

```vue
<script>
    export default {
        props: {
            name: {
                type: String,
                default: "kotlin-vue"
            }
        },
        render(h) {
            return (
                <h1>
                    { this.name }
                </h1>
            )
        }
    };
</script>
```

and then:

```vue
<script>
    import Hello from './Hello'
    export default {
        render(h) {
            return (
                <div>
                    <Hello/>
                </div>
            )
        }
    };
</script>
```

Here's the equivalent Kotlin code looks like:

```kotlin
package welcome

import vue.*
import vue.vdom.*
import vue.ext.*

interface WelcomeProps : VProps {
    var name: String
}

interface WelcomeComponent : VueComponent<VData, WelcomeProps, VOptions, VRefs>

object WelcomeOptions : VueOptions<
        VData, 
        WelcomeProps, 
        VComputed, 
        VWatch, 
        VOptions, 
        VRefs, 
        WelcomeComponent>(WelcomeComponent::class) {
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
```

VBuilder lets you construct your component's template using type-safe builders, similarly to JSX.

And here's how we can use this component in another component:

```kotlin
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
```

Although templates are enough convenient, they are don't allow you to use full programmatic power of a programming 
language. JSX may be a good alternative, but Kotlin with his type-safe builders, static type checking, modern language 
constructions may significantly facilitate development of complex ui systems. In addition, may be you don't know, but 
[Vue's templates actually are compiled into render functions](https://vuejs.org/v2/guide/render-function.html#Template-Compilation).