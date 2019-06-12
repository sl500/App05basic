package com.example.app05basic

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateClean()

        createDummyLists()

    }

    private fun onCreateClean() {
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun createDummyLists() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        /*var dummyMutableList = mutableListOf<testObject>(
            testObject("name1",1,1.1F),
            testObject("name2",2,2.1F),
            testObject("name3",3,3.1F)
        )
        var dummySet = mutableSetOf<testObject>(
            testObject("name1",1,1.1F),
            testObject("name2",2,2.1F),
            testObject("name3",3,3.1F)
        )
        var dummyList = mutableSetOf<testObject>(
            testObject("name1",1,1.1F),
            testObject("name2",2,2.1F),
            testObject("name3",3,3.1F)
        )*/
        var list1 = mutableListOf<daten>(
            DatenpunktInt("things1",1, LocalDateTime.now(), LocalDateTime.now(),11),
            DatenpunktInt("things1",2, LocalDateTime.now(), LocalDateTime.now(),12),
            DatenpunktInt("things1",3, LocalDateTime.now(), LocalDateTime.now(),13),
            DatenpunktInt("things1",4, LocalDateTime.now(), LocalDateTime.now(),14)
        )
        var things1: Things = Things("t1",1, LocalDateTime.now(), LocalDateTime.now(),list1)

        var list2 = mutableListOf<daten>(
            DatenpunktInt("things2",1, LocalDateTime.now(), LocalDateTime.now(),21),
            DatenpunktInt("things2",2, LocalDateTime.now(), LocalDateTime.now(),22),
            DatenpunktInt("things2",3, LocalDateTime.now(), LocalDateTime.now(),23),
            DatenpunktInt("things2",4, LocalDateTime.now(), LocalDateTime.now(),24)
        )
        var things2: Things = Things("t1",1, LocalDateTime.now(), LocalDateTime.now(),list2)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class Things(name:String, id: Int,createDate: java.time.LocalDateTime, updateDate: java.time.LocalDateTime, datenliste: kotlin.collections.MutableList<daten> ) : thingsToTrack {
    //werte von interface daten
    override var name: String = name
    override var id: Int = id
    override var createDate : java.time.LocalDateTime = createDate
    override var updateDate : java.time.LocalDateTime = updateDate
    override var datenliste: kotlin.collections.MutableList<daten> = datenliste
}
class DatenpunktInt(parentname:String, id: Int,createDate: java.time.LocalDateTime, updateDate: java.time.LocalDateTime, value: Int):datenpunktInt{
    override var name: String = parentname
    override var id: Int = id
    override var createDate : java.time.LocalDateTime = createDate
    override var updateDate : java.time.LocalDateTime = updateDate

    override var value: Int = value
}

abstract class daten1 {
    abstract var name: String
    //abstract var id: Int
    //abstract var createDate : java.time.LocalDateTime
    //abstract var updateDate : java.time.LocalDateTime
}
interface daten {
    var name: String
    var id: Int
    var createDate : java.time.LocalDateTime
    var updateDate : java.time.LocalDateTime
}
interface datenpunktInt : daten{
    var value: Int
}
interface datenpunktFloat : daten{
    var value: Float
}
interface datenpunktBoolean : daten{
    var value: Boolean
}

interface thingsToTrack : daten{
    // set und mutablecollection
    //var datenliste1: kotlin.collections.MutableSet<daten>        //A generic unordered collection of elements that supports adding and removing elements and does not support duplicate elements.
    // list and mutablecollection
    var datenliste: kotlin.collections.MutableList<daten>       //A generic   ordered collection of elements that supports adding and removing elements.
    // collection and mutableiterable
    //var datenliste3: kotlin.collections.MutableCollection<daten> //A generic           collection of elements that supports adding and removing elements.
    // iterable
    //var datenliste4: kotlin.collections.MutableIterable<daten>
    // map
    //var datenliste5: kotlin.collections.MutableMap<daten> //A modifiable collection that holds pairs of objects (keys and values) and supports efficiently retrieving the value corresponding to each key. Map keys are unique; the map holds only one value for each key
}