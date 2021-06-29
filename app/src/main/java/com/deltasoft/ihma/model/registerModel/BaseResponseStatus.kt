package com.deltasoft.ihma.model.registerModel

open class BaseResponseStatus<T>: BaseResponse() {
    var Data: T ?=null
}