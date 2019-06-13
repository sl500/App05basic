package com.example.app05basic

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.core.widget.NestedScrollView

import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    var listOfThings = mutableListOf<Things>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateClean()

        createDummyLists()
        var addThingContainerToHideOrShow = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.addNewThingContainer)
        addThingContainerToHideOrShow.visibility = View.GONE

        // add amount
        val btnAddThingToTrack = findViewById<Button>(R.id.btnAdd)
        btnAddThingToTrack?.setOnClickListener { makeAddNewThingAreaVisible() }
        // add group
        val btnListAllThingsInThingsScrollable = findViewById<Button>(R.id.btnListAllThings)
        btnListAllThingsInThingsScrollable?.setOnClickListener { listAllThingsInThingScrollable() }

        // del all of id
        val btnDel = findViewById<Button>(R.id.btnDel)
        btnDel?.setOnClickListener { deleteAll() }

        //dell this one
        val btnDelOne = findViewById<Button>(R.id.btnDelOne)
        btnDelOne?.setOnClickListener { deleteOne(1) }

        //btn Add new Thing to Thing list
        val btnAddNewThingToThingList = findViewById<Button>(R.id.btnAddNewThing)
        btnAddNewThingToThingList?.setOnClickListener{addNewThingToThingList()}
    }

    private fun addNewThingToThingList() {
        // hole name und wertart für das neue thing
        val etAddNewThingName = findViewById<EditText>(R.id.etNewThingName)
        val etAddNewThingValue = findViewById<EditText>(R.id.etNewThingValue)

        /*
        when (etAddNewThingValue.text.toString()) {
            "i" -> var newThing = Things(etAddNewThingName.text.toString(),listOfThings.size, LocalDateTime.now(), LocalDateTime.now(),MutableList<daten>())
            /*"f" ->
            "b" -> */
        }
        */
        //var listX = mutableListOf<daten>()
        var newThing = Things(etAddNewThingName.text.toString(),listOfThings.size, LocalDateTime.now(), LocalDateTime.now(),mutableListOf<daten>())
        listOfThings.add(newThing)
        //new aufbauen
        listAllThingsInThingScrollable()

    }

    private fun onCreateClean() {
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    private fun listAllThingsInThingScrollable() {
        //leere zuerst die details liste
        deleteAll()
        clearDetailsScrollsList()
        //OKAY:("not implemented") //To change body of created functions use File | Settings | File Templates.

        //hole den fillContainer
        //val linearLayoutToFill = findViewById<LinearLayout>(R.id.llDetails)
        val linearLayoutToFill = findViewById<LinearLayout>(R.id.llThings)
        //hole die anzahl an elementen, die ich erstellen soll:
        val howManyNewElementsSource = findViewById<EditText>(R.id.et1)
        var howManyNew = howManyNewElementsSource.text.toString().toInt()
        //var linearLayoutForGroup = LinearLayout(this)

        howManyNew=listOfThings.size

        var i = 0
        //erstelle die anzahl an elementen und füge sie hinzu
        //for (i in 1..howManyNew) {
        for (item in listOfThings) {
            //i = 0
            val base = 1000
            //erstelle linearlayout als halter für die gruppe
            var llForGroup = LinearLayout(this)
            llForGroup.setId(i*10+base)
            llForGroup.setPadding(4)
            llForGroup.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            // Füge texte hinzu NAME
            var etName = EditText(this)
            //editTextGroup.setText(R.string.new_group_item_name)
            //etName.text=Editable!("test")
            var cs: CharSequence = listOfThings[i].name
            //etName.setText(cs)
            etName.setText(item.name)
            //etName.setText(listOfThings[i].name)
            etName.id=(base+i*10+1)
            llForGroup.addView(etName)

            // Füge texte hinzu id
            var etId = EditText(this)
            //editTextGroup.setText(R.string.new_group_item_name)
            etId.setText(item.id.toString())
            etId.id=base+i*10+2
            llForGroup.addView(etId)


            // add SHOW Datapoints button
            var btnShowDatapointsOfThing = Button(this)
            //btnShow1.setText(R.string.show_text)
            btnShowDatapointsOfThing.text = "s"+(base+i*10+3).toString()
            //btnShow.setText(i)
            //btnShow.setText(1000+i)
            btnShowDatapointsOfThing.setId(base+i*10+3)

            btnShowDatapointsOfThing.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnShowDatapointsOfThing.setOnClickListener {
                this.showDatapointsOfThing(item,i)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnShowDatapointsOfThing)

            // add button Delete
            var btnDelete = Button(this)
            btnDelete.text = "-"+(base+i*10+4).toString()
            //btnShow.setText(i)
            //btnShow.setText(1000+i)
            btnDelete.setId(base+i*10+4)

            btnDelete.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnDelete.setOnClickListener {
                this.deleteThing(btnDelete)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnDelete)
            // Add element to LinearLayout

            // add button update
            var btnUpdate = Button(this)
            btnUpdate.text = "u"+(base+i*10+5).toString()
            //btnShow.setText(i)
            //btnShow.setText(1000+i)
            btnUpdate.setId(base+i*10+5)

            btnUpdate.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnUpdate.setOnClickListener {
                this.deleteThing(btnUpdate)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnUpdate)

/*            */
            // Add ll to LinearLayoutToFill
            linearLayoutToFill?.addView(llForGroup)
            i++
        }
    }

    private fun deleteOne(idParamToDelte: Int) {
        //R.id.editTextOutput
        //idParamToDelte
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val editTextNumber = findViewById<EditText>(R.id.et2)
        val linearLayout = findViewById<LinearLayout>(R.id.llThings)
        //linearLayout?.removeViewAt(editTextNumber.text.toString().toInt())
        var intNumberIdToDelete = editTextNumber.text.toString().toInt()

        if (intNumberIdToDelete < 10000) {
            var elementToDelete = findViewById<Button>(intNumberIdToDelete)
            linearLayout?.removeView(elementToDelete)
        }
        if (intNumberIdToDelete > 10000) {
            intNumberIdToDelete = intNumberIdToDelete / 10
            intNumberIdToDelete = intNumberIdToDelete * 10


            var elementToDelete = findViewById<LinearLayout>(intNumberIdToDelete)
            linearLayout?.removeView(elementToDelete)
        }
        //linearLayout?.removeView(elementToDelete)
        editTextNumber.setText("output")

    }

    private fun deleteAll() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val linearLayout = findViewById<LinearLayout>(R.id.llThings)
        linearLayout?.removeAllViews()
    }

    private fun makeAddNewThingAreaVisible() {
        var addThingContainerToHideOrShow = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.addNewThingContainer)
        //addThingContainerToHideOrShow.visibility = View.VISIBLE
        if  (addThingContainerToHideOrShow.visibility == View.VISIBLE) {
            addThingContainerToHideOrShow.visibility = View.GONE
            // Werte zurücksetzen
            var etAddNewThingName = findViewById<EditText>(R.id.etNewThingName)
            etAddNewThingName.setText("newThing")
            var etAddNewThingValue = findViewById<EditText>(R.id.etNewThingValue)
            etAddNewThingValue.setText("ifb")
        } else {
            addThingContainerToHideOrShow.visibility = View.VISIBLE
        }

        //ALT////////////////////////////////////////
/*
        //OKAY:("not implemented") //To change body of created functions use File | Settings | File Templates.
        val linearLayout = findViewById<LinearLayout>(R.id.llThings)

        val editTextNumber = findViewById<EditText>(R.id.et1)
        val anzahlInt = editTextNumber.text.toString().toInt()
        //var anzahlInt = anzahlString.toInt()
        //var anzahlInt = 5
        //if (anzahlInt != null) {
        for (i in 1..anzahlInt) {
            var btnShow = Button(this)
            btnShow.setText(R.string.show_text)
            btnShow.hint=(1000+i).toString()
            //btnShow.setText(i)
            //btnShow.setText(1000+i)
            btnShow.setId(1000+i)

            btnShow.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnShow.setOnClickListener {
                this.clickBtn(btnShow)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }

            // Add Button to LinearLayout
            linearLayout?.addView(btnShow)
        }*/
    }

    private fun deleteThing(btn: Button) {

        // funktion für klicken der geaddeden buttons, macht toast and füllt output textfield
        //OKAY("not implemented") //To change body of created functions use File | Settings | File Templates.
        //TODO("war hilfsbtn, sollte funktion bekommen entweder delete ")
        val editTextOutput = findViewById<EditText>(R.id.et2)
        editTextOutput.setText(btn.id.toString())

        Toast.makeText(this@MainActivity,  R.string.welcome_message, Toast.LENGTH_LONG).show()
    }
    private fun showDatapointsOfThing(thing: Things, id: Int) {
        // leere zuerst details view
        clearDetailsScrollsList()

        // funktion für klicken der geaddeden buttons, macht toast and füllt output textfield
        //OKAY:("not implemented") //To change body of created functions use File | Settings | File Templates.
        val editTextOutput = findViewById<EditText>(R.id.et2)
        //editTextOutput.setText(btn.id.toString())
        Toast.makeText(this@MainActivity,  R.string.welcome_message, Toast.LENGTH_LONG).show()
        ///////////////////////////
        val base = 2000
        // hole fillcontainer für header
        val linearLayoutToFillHeader = findViewById<LinearLayout>(R.id.llDetailsHeader)

        // füge text hinzu im header von was die aktuellen daten sind
        var etThing = EditText(this)
        etThing.setText(thing.name)
        etThing.id=(base+0*10+1)
        linearLayoutToFillHeader.addView(etThing)

        // add button to add datapoint
        var btnAddDatapoint = Button(this)
        btnAddDatapoint.text = "+ "+(base+0*10+2).toString()
        btnAddDatapoint.setId(base+0*10+2)
        btnAddDatapoint.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnAddDatapoint.setOnClickListener {
            //var dp: DatenpunktInt = item
            //todo:write addDataPoint()
            //this.dataRead(item,i)
            //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
        }
        linearLayoutToFillHeader.addView(btnAddDatapoint)

        // add button to add datapoint
        var btnClearDetailsList = Button(this)
        btnClearDetailsList.text = "c"+(base+0*10+3).toString()
        btnClearDetailsList.setId(base+0*10+3)
        btnClearDetailsList.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnClearDetailsList.setOnClickListener {
            //var dp: DatenpunktInt = item
            //OKAY:write add clearDetailsScrollsList()
            //this.dataRead(item,i)
            clearDetailsScrollsList()
            //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
        }
        linearLayoutToFillHeader.addView(btnClearDetailsList)


        // add button to add datapoint
        var btnDeleteAllDatapoint = Button(this)
        btnDeleteAllDatapoint.text = "D"+(base+0*10+4).toString()
        btnDeleteAllDatapoint.setId(base+0*10+4)
        btnDeleteAllDatapoint.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnDeleteAllDatapoint.setOnClickListener {
            //var dp: DatenpunktInt = item
            //OKAY:write add delteAllDatapoints()
            delteAllDatapoints(thing)
            //this.dataRead(item,i)
            //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
        }
        linearLayoutToFillHeader.addView(btnDeleteAllDatapoint)


         //hole den fillContainer für datenpunkte
        val linearLayoutToFill = findViewById<LinearLayout>(R.id.llDetails)
        //val howManyNewElementsSource = findViewById<EditText>(R.id.et1)
        //var howManyNew = howManyNewElementsSource.text.toString().toInt()
        //var linearLayoutForGroup = LinearLayout(this)

        //howManyNew=listOfThings.size

        var i = 0
        //erstelle die anzahl an elementen und füge sie hinzu
        //for (i in 1..howManyNew) {
        for (item in thing.datenliste) {
            //i = 0
            //val base = 2000
            //erstelle linearlayout als halter für die gruppe
            var llForGroup = LinearLayout(this)
            llForGroup.setId(i*10+base)
            llForGroup.setPadding(4)
            llForGroup.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            //DatenpunktInt("things2",1, LocalDateTime.now(), LocalDateTime.now(),21),
            //string id.toString() value.toString() btn update, delete, add

            // Füge texte hinzu NAME
            var etName = EditText(this)
            etName.setText(item.name)
            etName.id=(base+i*10+1)
            llForGroup.addView(etName)

            // Füge texte hinzu id
            var etId = EditText(this)
            etId.setText(item.id.toString())
            etId.id=base+i*10+2
            llForGroup.addView(etId)

            // Füge texte hinzu value
            var etValue = EditText(this)
            when (item) {
                is datenpunktInt -> etValue.setText((item).value.toString())
                is datenpunktFloat -> etValue.setText((item).value.toString())
                is datenpunktBoolean -> etValue.setText((item).value.toString())
            }
            etValue.id=base+i*10+3
            llForGroup.addView(etValue)

            // add READ button
            var btnRead = Button(this)
            btnRead.text = "+"+(base+i*10+4).toString()
            btnRead.setId(base+i*10+4)
            btnRead.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnRead.setOnClickListener {
                //var dp: DatenpunktInt = item
                //todo:write dataRead
                //this.dataRead(item,i)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnRead)

            // add button Delete
            var btnDelete = Button(this)
            btnDelete.text = "-"+(base+i*10+5).toString()

            btnDelete.setId(base+i*10+5)

            btnDelete.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnDelete.setOnClickListener {
                //todo write datenDelete
                //this.clickBtn(btnDelete)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnDelete)
            // Add element to LinearLayout

            // add button update
            var btnUpdate = Button(this)
            btnUpdate.text = "u"+(base+i*10+6).toString()
            btnUpdate.setId(base+i*10+6)

            btnUpdate.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnUpdate.setOnClickListener {
                // todo write datenUpdate
                //this.clickBtn(btnUpdate)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnUpdate)

/*            */
            // Add ll to LinearLayoutToFill
            linearLayoutToFill?.addView(llForGroup)
            i++
        }

    }

    private fun delteAllDatapoints(thing: Things) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        thing.datenliste.removeIf { item -> item.id > 0 }
        clearDetailsScrollsList()
    }

    private fun clearDetailsScrollsList() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val linearLayoutToClear = findViewById<LinearLayout>(R.id.llDetails)
        linearLayoutToClear.removeAllViews()
        val linearLayoutToClear2 = findViewById<LinearLayout>(R.id.llDetailsHeader)
        linearLayoutToClear2.removeAllViews()
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
        var things1: Things = Things("t1_kg",1, LocalDateTime.now(), LocalDateTime.now(),list1)

        var list2 = mutableListOf<daten>(
            DatenpunktInt("things2",1, LocalDateTime.now(), LocalDateTime.now(),21),
            DatenpunktInt("things2",2, LocalDateTime.now(), LocalDateTime.now(),22),
            DatenpunktInt("things2",3, LocalDateTime.now(), LocalDateTime.now(),23),
            DatenpunktInt("things2",4, LocalDateTime.now(), LocalDateTime.now(),24)
        )
        var things2: Things = Things("t2_cm",2, LocalDateTime.now(), LocalDateTime.now(),list2)

        listOfThings = mutableListOf<Things>(things1,things2)
        //var n = listOfThings.size
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
class MyNestedScrollView(context: Context, attrs: AttributeSet?) : NestedScrollView(context, attrs) {

    /**/override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, 0, 0, type)
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return super.onNestedFling(target, velocityX, velocityY, true)
    }
    /**/
}
