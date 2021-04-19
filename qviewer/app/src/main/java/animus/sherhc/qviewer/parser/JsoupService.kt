package animus.sherhc.qviewer.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class JsoupService(url: String) {
    private val document: Document = Jsoup.connect(url)
        .get()


    fun Element.getExtra(attr: String) = this.attr(attr)


    /**
     * 对Document进行降噪
     * @param document
     * @return Document
     */

    fun denoiseForDoc(document: Document): Document = document.apply {
        getElementsByTag("script").remove()
        getElementsByTag("style").remove()
        getElementsByTag("select").remove()
        getElementsByTag("link").remove()
        getElementsByTag("input").remove()
        getElementsByTag("object").remove()
        getElementsByTag("textarea").remove()
        getElementsByAttributeValue("display", "none").remove()
        getElementsByAttributeValueContaining("style", "display:none").remove()
        getElementsByAttributeValueContaining("style", "overflow: hidden").remove()
    }
}