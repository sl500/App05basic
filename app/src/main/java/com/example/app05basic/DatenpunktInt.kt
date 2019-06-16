package com.example.app05basic

import java.time.LocalDateTime

class DatenpunktInt(parentname:String, id: Int, createDate: LocalDateTime, updateDate: LocalDateTime, value: Int):
    datenpunktInt {
    override var name: String = parentname
    override var id: Int = id
    override var createDate : LocalDateTime = createDate
    override var updateDate : LocalDateTime = updateDate

    override var value: Int = value
}