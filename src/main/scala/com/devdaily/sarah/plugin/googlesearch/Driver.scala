package com.devdaily.sarah.plugin.googlesearch

object Driver extends App {

    // 1
    val s = "google foo bars baz"
    println("should be true: " + SearchUtils.matchesSearchPattern(s))

    // 2
    val searchText = SearchUtils.getSearchString(s)
    println("should be 'foo bar baz': " + searchText)
    
    // 3
    val url = SearchUtils.buildUrl(searchText.get)
    println(url)

    // 4
    SearchUtils.runAppleScriptCommand(s"""open location "$url"""")
}