package com.devdaily.sarah.plugin.googlesearch

import com.devdaily.sarah.plugins.SarahPlugin
import com.devdaily.sarah.Sarah
import com.devdaily.sarah.plugins.PleaseSay
import akka.actor.ActorSystem
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.regex.Pattern

/**
 * TODO put the text responses below [here you are, i had a problem] into a properties file.
 */
class GoogleSearchPlugin extends SarahPlugin {

    // used by any Future calls
    implicit val actorSystem = ActorSystem("CurrentTimeActorSystem")

    // TODO verify - i don't think Sarah uses this any more
    val phrasesICanHandle = List("google")

    // sarah calls this automatically
    def textPhrasesICanHandle: List[String] = {
        return phrasesICanHandle
    }

    // this method tells Sarah whether we can handle the given phrase (true) or not (false)
    def handlePhrase(phrase: String): Boolean = {
        val cleanPhrase = phrase.trim.toLowerCase
        if (SearchUtils.matchesSearchPattern(cleanPhrase)) {
            val searchText = SearchUtils.getSearchString(cleanPhrase)
            searchText match {
                case Some(text) => {
                    val f = Future { brain ! PleaseSay("Here you are.") }
                    val url = SearchUtils.buildUrl(text)
                    SearchUtils.runAppleScriptCommand(s"""open location "$url"""")
                }
                case None => {
                    val f = Future { brain ! PleaseSay("Sorry, I had a problem trying to do that.") }
                    return false
                }
            }
            return true
        } else {
            return false
        }
    }

    // nothing to do at startup
    def startPlugin = {}

    override def setPluginDirectory(dir: String) {
        // do nothing 
    }

}






