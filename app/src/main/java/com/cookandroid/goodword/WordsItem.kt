package com.cookandroid.goodword

class WordsItem {

    var icons: String? = ""
    var name: String? = null

    constructor(icons: String?, name: String?) {
        this.icons = icons
        this.name = name
    }

    constructor(): this("","")
}