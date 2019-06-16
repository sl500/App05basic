package com.example.app05basic

import java.time.LocalDateTime

class Things(name:String, id: Int, createDate: LocalDateTime, updateDate: LocalDateTime, datenliste: kotlin.collections.MutableList<daten> ) :
    thingsToTrack {
    //werte von interface daten
    override var name: String = name
    override var id: Int = id
    override var createDate : LocalDateTime = createDate
    override var updateDate : LocalDateTime = updateDate
    override var datenliste: kotlin.collections.MutableList<daten> = datenliste
}