package com.example.buttsweetsfinal.network

import java.io.Serializable

open class BaseApiResponse(
    var collection: List<Any> = listOf()
):Serializable