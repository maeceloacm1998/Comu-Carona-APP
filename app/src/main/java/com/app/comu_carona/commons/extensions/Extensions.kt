package com.app.comu_carona.commons.extensions

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val PLACEHOLDER = "PLACEHOLDER_SLASH"

fun String.encodeParameter(): String {
    return URLEncoder.encode(this.replace("/", PLACEHOLDER), StandardCharsets.UTF_8.toString())
}

fun String.decodeParameter(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString()).replace(PLACEHOLDER, "/")
}