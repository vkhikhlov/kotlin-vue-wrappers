@file:Suppress("Unused")

package vue.vdom

import kotlinx.html.*
import kotlinx.html.attributes.enumEncode
import vue.VBuilder

@Suppress("Unused")
fun Template.root(classes: String? = null, init: RootNode<DIV>.() -> Unit = {}): RootNode<DIV> {
    rootNode = RootNode(createElement, { DIV(attributesMapOf("class", classes), it) })
    rootNode.init()
    return rootNode
}

inline fun <T : Tag> VBuilder.initTag(block: VDOMBuilder<T>.() -> Unit = {}, noinline factory: (TagConsumer<Unit>) -> T) =
        child(VDOMBuilder(createElement, factory).apply(block).build())

inline fun VBuilder.a(href: String? = null, target: String? = null, classes: String? = null, block: VDOMBuilder<A>.() -> Unit = {}) = initTag(block) { A(attributesMapOf("href", href, "target", target, "class", classes), it) }

inline fun VBuilder.abbr(classes: String? = null, block: VDOMBuilder<ABBR>.() -> Unit = {}) = initTag(block) { ABBR(attributesMapOf("class", classes), it) }

inline fun VBuilder.address(classes: String? = null, block: VDOMBuilder<ADDRESS>.() -> Unit = {}) = initTag(block) { ADDRESS(attributesMapOf("class", classes), it) }

inline fun VBuilder.area(shape: AreaShape? = null, alt: String? = null, classes: String? = null, block: VDOMBuilder<AREA>.() -> Unit = {}) = initTag(block) { AREA(attributesMapOf("Shape", shape?.enumEncode(), "alt", alt, "class", classes), it) }

inline fun VBuilder.article(classes: String? = null, block: VDOMBuilder<ARTICLE>.() -> Unit = {}) = initTag(block) { ARTICLE(attributesMapOf("class", classes), it) }

inline fun VBuilder.aside(classes: String? = null, block: VDOMBuilder<ASIDE>.() -> Unit = {}) = initTag(block) { ASIDE(attributesMapOf("class", classes), it) }

inline fun VBuilder.audio(classes: String? = null, block: VDOMBuilder<AUDIO>.() -> Unit = {}) = initTag(block) { AUDIO(attributesMapOf("class", classes), it) }

inline fun VBuilder.b(classes: String? = null, block: VDOMBuilder<B>.() -> Unit = {}) = initTag(block) { B(attributesMapOf("class", classes), it) }

inline fun VBuilder.base(classes: String? = null, block: VDOMBuilder<BASE>.() -> Unit = {}) = initTag(block) { BASE(attributesMapOf("class", classes), it) }

inline fun VBuilder.bdi(classes: String? = null, block: VDOMBuilder<BDI>.() -> Unit = {}) = initTag(block) { BDI(attributesMapOf("class", classes), it) }

inline fun VBuilder.bdo(classes: String? = null, block: VDOMBuilder<BDO>.() -> Unit = {}) = initTag(block) { BDO(attributesMapOf("class", classes), it) }

inline fun VBuilder.blockQuote(classes: String? = null, block: VDOMBuilder<BLOCKQUOTE>.() -> Unit = {}) = initTag(block) { BLOCKQUOTE(attributesMapOf("class", classes), it) }

inline fun VBuilder.body(classes: String? = null, block: VDOMBuilder<BODY>.() -> Unit = {}) = initTag(block) { BODY(attributesMapOf("class", classes), it) }

inline fun VBuilder.br(classes: String? = null, block: VDOMBuilder<BR>.() -> Unit = {}) = initTag(block) { BR(attributesMapOf("class", classes), it) }

inline fun VBuilder.button(formEncType: ButtonFormEncType? = null, formMethod: ButtonFormMethod? = null, type: ButtonType? = null, classes: String? = null, block: VDOMBuilder<BUTTON>.() -> Unit = {}) = initTag(block) { BUTTON(attributesMapOf("formenctype", formEncType?.enumEncode(), "formmethod", formMethod?.enumEncode(), "type", type?.enumEncode(), "class", classes), it) }

inline fun VBuilder.canvas(classes: String? = null, block: VDOMBuilder<CANVAS>.() -> Unit = {}) = initTag(block) { CANVAS(attributesMapOf("class", classes), it) }

inline fun VBuilder.caption(classes: String? = null, block: VDOMBuilder<CAPTION>.() -> Unit = {}) = initTag(block) { CAPTION(attributesMapOf("class", classes), it) }

inline fun VBuilder.cite(classes: String? = null, block: VDOMBuilder<CITE>.() -> Unit = {}) = initTag(block) { CITE(attributesMapOf("class", classes), it) }

inline fun VBuilder.code(classes: String? = null, block: VDOMBuilder<CODE>.() -> Unit = {}) = initTag(block) { CODE(attributesMapOf("class", classes), it) }

inline fun VBuilder.col(classes: String? = null, block: VDOMBuilder<COL>.() -> Unit = {}) = initTag(block) { COL(attributesMapOf("class", classes), it) }

inline fun VBuilder.colGroup(classes: String? = null, block: VDOMBuilder<COLGROUP>.() -> Unit = {}) = initTag(block) { COLGROUP(attributesMapOf("class", classes), it) }

inline fun VBuilder.command(type: CommandType? = null, classes: String? = null, block: VDOMBuilder<COMMAND>.() -> Unit = {}) = initTag(block) { COMMAND(attributesMapOf("type", type?.enumEncode(), "class", classes), it) }

inline fun VBuilder.dataList(classes: String? = null, block: VDOMBuilder<DATALIST>.() -> Unit = {}) = initTag(block) { DATALIST(attributesMapOf("class", classes), it) }

inline fun VBuilder.dd(classes: String? = null, block: VDOMBuilder<DD>.() -> Unit = {}) = initTag(block) { DD(attributesMapOf("class", classes), it) }

inline fun VBuilder.del(classes: String? = null, block: VDOMBuilder<DEL>.() -> Unit = {}) = initTag(block) { DEL(attributesMapOf("class", classes), it) }

inline fun VBuilder.details(classes: String? = null, block: VDOMBuilder<DETAILS>.() -> Unit = {}) = initTag(block) { DETAILS(attributesMapOf("class", classes), it) }

inline fun VBuilder.dfn(classes: String? = null, block: VDOMBuilder<DFN>.() -> Unit = {}) = initTag(block) { DFN(attributesMapOf("class", classes), it) }

inline fun VBuilder.dialog(classes: String? = null, block: VDOMBuilder<DIALOG>.() -> Unit = {}) = initTag(block) { DIALOG(attributesMapOf("class", classes), it) }

inline fun VBuilder.div(classes: String? = null, block: VDOMBuilder<DIV>.() -> Unit = {}) = initTag(block) { DIV(attributesMapOf("class", classes), it) }

inline fun VBuilder.dl(classes: String? = null, block: VDOMBuilder<DL>.() -> Unit = {}) = initTag(block) { DL(attributesMapOf("class", classes), it) }

inline fun VBuilder.dt(classes: String? = null, block: VDOMBuilder<DT>.() -> Unit = {}) = initTag(block) { DT(attributesMapOf("class", classes), it) }

inline fun VBuilder.em(classes: String? = null, block: VDOMBuilder<EM>.() -> Unit = {}) = initTag(block) { EM(attributesMapOf("class", classes), it) }

inline fun VBuilder.embed(classes: String? = null, block: VDOMBuilder<EMBED>.() -> Unit = {}) = initTag(block) { EMBED(attributesMapOf("class", classes), it) }

inline fun VBuilder.fieldSet(classes: String? = null, block: VDOMBuilder<FIELDSET>.() -> Unit = {}) = initTag(block) { FIELDSET(attributesMapOf("class", classes), it) }

inline fun VBuilder.figcaption(classes: String? = null, block: VDOMBuilder<FIGCAPTION>.() -> Unit = {}) = initTag(block) { FIGCAPTION(attributesMapOf("class", classes), it) }

inline fun VBuilder.figure(classes: String? = null, block: VDOMBuilder<FIGURE>.() -> Unit = {}) = initTag(block) { FIGURE(attributesMapOf("class", classes), it) }

inline fun VBuilder.footer(classes: String? = null, block: VDOMBuilder<FOOTER>.() -> Unit = {}) = initTag(block) { FOOTER(attributesMapOf("class", classes), it) }

inline fun VBuilder.form(action: String? = null, encType: FormEncType? = null, method: FormMethod? = null, classes: String? = null, block: VDOMBuilder<FORM>.() -> Unit = {}) = initTag(block) { FORM(attributesMapOf("action", action, "enctype", encType?.enumEncode(), "method", method?.enumEncode(), "class", classes), it) }

inline fun VBuilder.h1(classes: String? = null, block: VDOMBuilder<H1>.() -> Unit = {}) = initTag(block) { H1(attributesMapOf("class", classes), it) }

inline fun VBuilder.h2(classes: String? = null, block: VDOMBuilder<H2>.() -> Unit = {}) = initTag(block) { H2(attributesMapOf("class", classes), it) }

inline fun VBuilder.h3(classes: String? = null, block: VDOMBuilder<H3>.() -> Unit = {}) = initTag(block) { H3(attributesMapOf("class", classes), it) }

inline fun VBuilder.h4(classes: String? = null, block: VDOMBuilder<H4>.() -> Unit = {}) = initTag(block) { H4(attributesMapOf("class", classes), it) }

inline fun VBuilder.h5(classes: String? = null, block: VDOMBuilder<H5>.() -> Unit = {}) = initTag(block) { H5(attributesMapOf("class", classes), it) }

inline fun VBuilder.h6(classes: String? = null, block: VDOMBuilder<H6>.() -> Unit = {}) = initTag(block) { H6(attributesMapOf("class", classes), it) }

inline fun VBuilder.head(block: VDOMBuilder<HEAD>.() -> Unit = {}) = initTag(block) { HEAD(emptyMap, it) }

inline fun VBuilder.header(classes: String? = null, block: VDOMBuilder<HEADER>.() -> Unit = {}) = initTag(block) { HEADER(attributesMapOf("class", classes), it) }

inline fun VBuilder.hGroup(classes: String? = null, block: VDOMBuilder<HGROUP>.() -> Unit = {}) = initTag(block) { HGROUP(attributesMapOf("class", classes), it) }

inline fun VBuilder.hr(classes: String? = null, block: VDOMBuilder<HR>.() -> Unit = {}) = initTag(block) { HR(attributesMapOf("class", classes), it) }

inline fun VBuilder.html(block: VDOMBuilder<HTML>.() -> Unit = {}) = initTag(block) { HTML(emptyMap, it) }

inline fun VBuilder.i(classes: String? = null, block: VDOMBuilder<I>.() -> Unit = {}) = initTag(block) { I(attributesMapOf("class", classes), it) }

inline fun VBuilder.iframe(sandbox: IframeSandbox? = null, classes: String? = null, block: VDOMBuilder<IFRAME>.() -> Unit = {}) = initTag(block) { IFRAME(attributesMapOf("sandbox", sandbox?.enumEncode(), "class", classes), it) }

inline fun VBuilder.img(alt: String? = null, src: String? = null, classes: String? = null, block: VDOMBuilder<IMG>.() -> Unit = {}) = initTag(block) { IMG(attributesMapOf("alt", alt, "src", src, "class", classes), it) }

inline fun VBuilder.input(type: InputType? = null, formEncType: InputFormEncType? = null, formMethod: InputFormMethod? = null, name: String? = null, classes: String? = null, block: VDOMBuilder<INPUT>.() -> Unit = {}) = initTag(block) { INPUT(attributesMapOf("type", type?.enumEncode(), "formenctype", formEncType?.enumEncode(), "formmethod", formMethod?.enumEncode(), "name", name, "class", classes), it) }

inline fun VBuilder.ins(classes: String? = null, block: VDOMBuilder<INS>.() -> Unit = {}) = initTag(block) { INS(attributesMapOf("class", classes), it) }

inline fun VBuilder.kbd(classes: String? = null, block: VDOMBuilder<KBD>.() -> Unit = {}) = initTag(block) { KBD(attributesMapOf("class", classes), it) }

inline fun VBuilder.keyGen(keyType: KeyGenKeyType? = null, classes: String? = null, block: VDOMBuilder<KEYGEN>.() -> Unit = {}) = initTag(block) { KEYGEN(attributesMapOf("keytype", keyType?.enumEncode(), "class", classes), it) }

inline fun VBuilder.label(classes: String? = null, block: VDOMBuilder<LABEL>.() -> Unit = {}) = initTag(block) { LABEL(attributesMapOf("class", classes), it) }

inline fun VBuilder.legend(classes: String? = null, block: VDOMBuilder<LEGEND>.() -> Unit = {}) = initTag(block) { LEGEND(attributesMapOf("class", classes), it) }

inline fun VBuilder.li(classes: String? = null, block: VDOMBuilder<LI>.() -> Unit = {}) = initTag(block) { LI(attributesMapOf("class", classes), it) }

inline fun VBuilder.link(href: String? = null, rel: String? = null, type: String? = null, block: VDOMBuilder<LINK>.() -> Unit = {}) = initTag(block) { LINK(attributesMapOf("href", href, "rel", rel, "type", type), it) }

inline fun VBuilder.map(name: String? = null, classes: String? = null, block: VDOMBuilder<MAP>.() -> Unit = {}) = initTag(block) { MAP(attributesMapOf("name", name, "class", classes), it) }

inline fun VBuilder.mark(classes: String? = null, block: VDOMBuilder<MARK>.() -> Unit = {}) = initTag(block) { MARK(attributesMapOf("class", classes), it) }

inline fun VBuilder.math(classes: String? = null, block: VDOMBuilder<MATH>.() -> Unit = {}) = initTag(block) { MATH(attributesMapOf("class", classes), it) }

inline fun VBuilder.mathml(classes: String? = null, block: VDOMBuilder<MATHML>.() -> Unit = {}) = initTag(block) { MATHML(attributesMapOf("class", classes), it) }

inline fun VBuilder.meta(name: String? = null, content: String? = null, block: VDOMBuilder<META>.() -> Unit = {}) = initTag(block) { META(attributesMapOf("name", name, "content", content), it) }

inline fun VBuilder.meter(classes: String? = null, block: VDOMBuilder<METER>.() -> Unit = {}) = initTag(block) { METER(attributesMapOf("class", classes), it) }

inline fun VBuilder.nav(classes: String? = null, block: VDOMBuilder<NAV>.() -> Unit = {}) = initTag(block) { NAV(attributesMapOf("class", classes), it) }

inline fun VBuilder.noScript(classes: String? = null, block: VDOMBuilder<NOSCRIPT>.() -> Unit = {}) = initTag(block) { NOSCRIPT(attributesMapOf("class", classes), it) }

inline fun VBuilder.object_(classes: String? = null, block: VDOMBuilder<OBJECT>.() -> Unit = {}) = initTag(block) { OBJECT(attributesMapOf("class", classes), it) }

inline fun VBuilder.ol(classes: String? = null, block: VDOMBuilder<OL>.() -> Unit = {}) = initTag(block) { OL(attributesMapOf("class", classes), it) }

inline fun VBuilder.optGroup(label: String? = null, classes: String? = null, block: VDOMBuilder<OPTGROUP>.() -> Unit = {}) = initTag(block) { OPTGROUP(attributesMapOf("label", label, "class", classes), it) }

inline fun VBuilder.option(classes: String? = null, block: VDOMBuilder<OPTION>.() -> Unit = {}) = initTag(block) { OPTION(attributesMapOf("class", classes), it) }

inline fun VBuilder.output(classes: String? = null, block: VDOMBuilder<OUTPUT>.() -> Unit = {}) = initTag(block) { OUTPUT(attributesMapOf("class", classes), it) }

inline fun VBuilder.p(classes: String? = null, block: VDOMBuilder<P>.() -> Unit = {}) = initTag(block) { P(attributesMapOf("class", classes), it) }

inline fun VBuilder.param(name: String? = null, value: String? = null, block: VDOMBuilder<PARAM>.() -> Unit = {}) = initTag(block) { PARAM(attributesMapOf("name", name, "value", value), it) }

inline fun VBuilder.pre(classes: String? = null, block: VDOMBuilder<PRE>.() -> Unit = {}) = initTag(block) { PRE(attributesMapOf("class", classes), it) }

inline fun VBuilder.progress(classes: String? = null, block: VDOMBuilder<PROGRESS>.() -> Unit = {}) = initTag(block) { PROGRESS(attributesMapOf("class", classes), it) }

inline fun VBuilder.q(classes: String? = null, block: VDOMBuilder<Q>.() -> Unit = {}) = initTag(block) { Q(attributesMapOf("class", classes), it) }

inline fun VBuilder.rp(classes: String? = null, block: VDOMBuilder<RP>.() -> Unit = {}) = initTag(block) { RP(attributesMapOf("class", classes), it) }

inline fun VBuilder.rt(classes: String? = null, block: VDOMBuilder<RT>.() -> Unit = {}) = initTag(block) { RT(attributesMapOf("class", classes), it) }

inline fun VBuilder.ruby(classes: String? = null, block: VDOMBuilder<RUBY>.() -> Unit = {}) = initTag(block) { RUBY(attributesMapOf("class", classes), it) }

inline fun VBuilder.samp(classes: String? = null, block: VDOMBuilder<SAMP>.() -> Unit = {}) = initTag(block) { SAMP(attributesMapOf("class", classes), it) }

inline fun VBuilder.script(type: String? = null, src: String? = null, block: VDOMBuilder<SCRIPT>.() -> Unit = {}) = initTag(block) { SCRIPT(attributesMapOf("type", type, "src", src), it) }

inline fun VBuilder.section(classes: String? = null, block: VDOMBuilder<SECTION>.() -> Unit = {}) = initTag(block) { SECTION(attributesMapOf("class", classes), it) }

inline fun VBuilder.select(classes: String? = null, block: VDOMBuilder<SELECT>.() -> Unit = {}) = initTag(block) { SELECT(attributesMapOf("class", classes), it) }

inline fun VBuilder.small(classes: String? = null, block: VDOMBuilder<SMALL>.() -> Unit = {}) = initTag(block) { SMALL(attributesMapOf("class", classes), it) }

inline fun VBuilder.source(classes: String? = null, block: VDOMBuilder<SOURCE>.() -> Unit = {}) = initTag(block) { SOURCE(attributesMapOf("class", classes), it) }

inline fun VBuilder.span(classes: String? = null, block: VDOMBuilder<SPAN>.() -> Unit = {}) = initTag(block) { SPAN(attributesMapOf("class", classes), it) }

inline fun VBuilder.strong(classes: String? = null, block: VDOMBuilder<STRONG>.() -> Unit = {}) = initTag(block) { STRONG(attributesMapOf("class", classes), it) }

inline fun VBuilder.style(type: String? = null, block: VDOMBuilder<STYLE>.() -> Unit = {}) = initTag(block) { STYLE(attributesMapOf("type", type), it) }

inline fun VBuilder.sub(classes: String? = null, block: VDOMBuilder<SUB>.() -> Unit = {}) = initTag(block) { SUB(attributesMapOf("class", classes), it) }

inline fun VBuilder.sup(classes: String? = null, block: VDOMBuilder<SUP>.() -> Unit = {}) = initTag(block) { SUP(attributesMapOf("class", classes), it) }

inline fun VBuilder.svg(classes: String? = null, block: VDOMBuilder<SVG>.() -> Unit = {}) = initTag(block) { SVG(attributesMapOf("class", classes), it) }

inline fun VBuilder.table(classes: String? = null, block: VDOMBuilder<TABLE>.() -> Unit = {}) = initTag(block) { TABLE(attributesMapOf("class", classes), it) }

inline fun VBuilder.tbody(classes: String? = null, block: VDOMBuilder<TBODY>.() -> Unit = {}) = initTag(block) { TBODY(attributesMapOf("class", classes), it) }

inline fun VBuilder.td(classes: String? = null, block: VDOMBuilder<TD>.() -> Unit = {}) = initTag(block) { TD(attributesMapOf("class", classes), it) }

inline fun VBuilder.textArea(rows: String? = null, cols: String? = null, wrap: TextAreaWrap? = null, classes: String? = null, block: VDOMBuilder<TEXTAREA>.() -> Unit = {}) = initTag(block) { TEXTAREA(attributesMapOf("rows", rows, "cols", cols, "wrap", wrap?.enumEncode(), "class", classes), it) }

inline fun VBuilder.tfoot(classes: String? = null, block: VDOMBuilder<TFOOT>.() -> Unit = {}) = initTag(block) { TFOOT(attributesMapOf("class", classes), it) }

inline fun VBuilder.th(scope: ThScope? = null, classes: String? = null, block: VDOMBuilder<TH>.() -> Unit = {}) = initTag(block) { TH(attributesMapOf("scope", scope?.enumEncode(), "class", classes), it) }

inline fun VBuilder.thead(classes: String? = null, block: VDOMBuilder<THEAD>.() -> Unit = {}) = initTag(block) { THEAD(attributesMapOf("class", classes), it) }

inline fun VBuilder.time(classes: String? = null, block: VDOMBuilder<TIME>.() -> Unit = {}) = initTag(block) { TIME(attributesMapOf("class", classes), it) }

inline fun VBuilder.title(block: VDOMBuilder<TITLE>.() -> Unit = {}) = initTag(block) { TITLE(emptyMap, it) }

inline fun VBuilder.tr(classes: String? = null, block: VDOMBuilder<TR>.() -> Unit = {}) = initTag(block) { TR(attributesMapOf("class", classes), it) }

inline fun VBuilder.ul(classes: String? = null, block: VDOMBuilder<UL>.() -> Unit = {}) = initTag(block) { UL(attributesMapOf("class", classes), it) }

inline fun VBuilder.htmlVar(classes: String? = null, block: VDOMBuilder<VAR>.() -> Unit = {}) = initTag(block) { VAR(attributesMapOf("class", classes), it) }

inline fun VBuilder.video(classes: String? = null, block: VDOMBuilder<VIDEO>.() -> Unit = {}) = initTag(block) { VIDEO(attributesMapOf("class", classes), it) }