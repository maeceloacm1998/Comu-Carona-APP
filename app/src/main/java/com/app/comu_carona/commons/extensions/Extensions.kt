package com.app.comu_carona.commons.extensions

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeParameter(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}

fun String.decodeParameter(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}