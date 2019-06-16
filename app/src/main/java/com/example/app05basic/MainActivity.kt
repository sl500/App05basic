package com.example.app05basic

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding

import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    var listOfThings = mutableListOf<Things>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateClean()

        createDummyLists()

        refreshThingsScrollable()

        configerButons()

        //refreshThingsScrollable()
    }

    private fun configerButons() {
        //TOP BUTTONS:
        // add amount
        val btnAddThingToTrack = findViewById<Button>(R.id.btnAdd)
        btnAddThingToTrack?.setOnClickListener {
            switchElementVisibility(R.id.addNewThingContainer)
            findEditTextAndSetText("thing", R.id.etNewThingName)
            findEditTextAndSetText("ifb", R.id.etNewThingValue)

        }

        // list things
        //val btnListAllThingsInThingsScrollable = findViewById<Button>(R.id.btnListAllThings)
        //btnListAllThingsInThingsScrollable?.setOnClickListener { refreshThingsScrollable() }

        // del all of id
        //val btnDel = findViewById<Button>(R.id.btnDel)
        //btnDel?.setOnClickListener { removeAllViewsFromThingsLL() }

        //dell this one
        //val btnDelOne = findViewById<Button>(R.id.btnDelOne)
        //btnDelOne?.setOnClickListener { removeOneSpecificViewGroupFromThingLL(1) }


        //btn Add new Thing to Thing list
        val btnAddNewThingToThingList = findViewById<Button>(R.id.btnAddNewThing)
        btnAddNewThingToThingList?.setOnClickListener { addNewThingToThingListAndRefresh() }
    }

    private fun findEditTextAndSetText(s: String, id: Int) {
        var et = findViewById<EditText>(id)
        et.setText(s)
    }

    //THINGS

    //NEW
    //listAllThingsInThingScrollable
    private fun refreshThingsScrollable() {
        //leere zuerst beide View listen
        removeAllViewsFromThingsLL()
        removeViewsFromLLDetailsAndLLDetailsHeaderInDetailsScrollablelist()

        //hole den fillContainer
        //val linearLayoutToFill = findViewById<LinearLayout>(R.id.llDetails)
        val linearLayoutToFill = findViewById<LinearLayout>(R.id.llThings)
        //hole die anzahl an elementen, die ich erstellen soll:
        val howManyNewElementsSource = findViewById<EditText>(R.id.et1)
        var howManyNew = howManyNewElementsSource.text.toString().toInt()
        //var linearLayoutForGroup = LinearLayout(this)

        howManyNew=listOfThings.size

        //erstelle die anzahl an elementen und füge sie hinzu
        //for (i in 1..howManyNew) {
        for ((i, item) in listOfThings.withIndex()) {
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

            //füge nummer hinzu
            var etNummer = TextView(this)
            etNummer.setText(listOfThings.indexOf(item).toString())
            etNummer.setId(base+i*10+8)
            llForGroup.addView(etNummer)


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
                this.refreshDatapointsDetailsScrollable(item)
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
                this.writeButtonIdStringToetOutputSoThatRemoveOneSpecificKnowsWhatToRemove(btnDelete)
                this.deleteThisThingFromThingListAndRefreshList(i,item)
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
                this.writeButtonIdStringToetOutputSoThatRemoveOneSpecificKnowsWhatToRemove(btnUpdate)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnUpdate)

            // Füge texte hinzu id
            /**/
            var etId = EditText(this)
            //editTextGroup.setText(R.string.new_group_item_name)
            etId.setText(item.id.toString())
            etId.id=base+i*10+2
            llForGroup.addView(etId)

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

            // Add ll to LinearLayoutToFill
            linearLayoutToFill?.addView(llForGroup)
        }
    }

    private fun deleteThisThingFromThingListAndRefreshList(i: Int, item: Things) {
        //delete item
        listOfThings.removeAt(i)
        //listOfThings.remove(item)
        refreshThingsScrollable()
    }

    //NEW
    private fun addNewThingToThingListAndRefresh() {
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
        var newThing = Things(etAddNewThingName.text.toString(),(1000*Math.random()).toInt(), LocalDateTime.now(), LocalDateTime.now(),mutableListOf<daten>())
        listOfThings.add(newThing)
        //new aufbauen
        refreshThingsScrollable()
    }

    //DATAPOINTS

    // showDatapointsOfThing
    private fun refreshDatapointsDetailsScrollable(thing: Things) {
        // leere zuerst details view
        removeViewsFromLLDetailsAndLLDetailsHeaderInDetailsScrollablelist()

        val base = 2000

        fillDatapointsDetailsScrollableHeader(thing, base)

        fillDatapointsDetailsScrollableList(thing, base)

    }

    private fun fillDatapointsDetailsScrollableList(thing: Things, base: Int) {
        //hole den fillContainer für datenpunkte
        val linearLayoutToFill = findViewById<LinearLayout>(R.id.llDetails)
        //val howManyNewElementsSource = findViewById<EditText>(R.id.et1)
        //var howManyNew = howManyNewElementsSource.text.toString().toInt()
        //var linearLayoutForGroup = LinearLayout(this)

        //howManyNew=listOfThings.size

        //erstelle die anzahl an elementen und füge sie hinzu
        //for (i in 1..howManyNew) {
        for ((i, item) in thing.datenliste.withIndex()) {
            //i = 0
            //val base = 2000
            //erstelle linearlayout als halter für die gruppe
            var llForGroup = LinearLayout(this)
            llForGroup.setId(i * 10 + base)
            llForGroup.setPadding(4)
            llForGroup.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            //DatenpunktInt("things2",1, LocalDateTime.now(), LocalDateTime.now(),21),
            //string id.toString() value.toString() btn update, delete, add

            //füge nummer hinzu
            var etNummerDatapoint = TextView(this)
            etNummerDatapoint.setText(thing.datenliste.indexOf(item).toString())
            etNummerDatapoint.setId(base+i*10+8)
            llForGroup.addView(etNummerDatapoint)

            // add READ button
            var btnRead = Button(this)
            btnRead.text = "+" + (base + i * 10 + 4).toString()
            btnRead.setId(base + i * 10 + 4)
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
            btnDelete.text = "-" + (base + i * 10 + 5).toString()

            btnDelete.setId(base + i * 10 + 5)

            btnDelete.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            btnDelete.setOnClickListener {
                //todo write datenDelete
                //this.clickBtn(btnDelete)
                deleteThisDatapointFromListAndRefresh(i,item, thing)
                //Toast.makeText(this@MainActivity, R.string.welcome_message, Toast.LENGTH_LONG).show()
            }
            llForGroup.addView(btnDelete)
            // Add element to LinearLayout

            // add button update
            var btnUpdate = Button(this)
            btnUpdate.text = "u" + (base + i * 10 + 6).toString()
            btnUpdate.setId(base + i * 10 + 6)

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

            // Füge texte hinzu NAME
            var etName = EditText(this)
            etName.setText(item.name)
            etName.id = (base + i * 10 + 1)
            llForGroup.addView(etName)

            // Füge texte hinzu id
            var etId = EditText(this)
            etId.setText(item.id.toString())
            etId.id = base + i * 10 + 2
            llForGroup.addView(etId)

            // Füge texte hinzu value
            var etValue = EditText(this)
            when (item) {
                is datenpunktInt -> etValue.setText((item).value.toString())
                is datenpunktFloat -> etValue.setText((item).value.toString())
                is datenpunktBoolean -> etValue.setText((item).value.toString())
            }
            etValue.id = base + i * 10 + 3
            llForGroup.addView(etValue)


            // Add ll to LinearLayoutToFill
            linearLayoutToFill?.addView(llForGroup)
        }
    }

    private fun deleteThisDatapointFromListAndRefresh(i: Int, item: daten, thing: Things) {
        thing.datenliste.remove(item)
        refreshDatapointsDetailsScrollable(thing)
    }

    private fun fillDatapointsDetailsScrollableHeader(thing: Things, base: Int) {
        // hole fillcontainer für header
        val linearLayoutToFillHeader = findViewById<LinearLayout>(R.id.llDetailsHeader)

        // füge text hinzu im header von was die aktuellen daten sind
        var etThing = EditText(this)
        etThing.setText(thing.name)
        etThing.id = (base + 0 * 10 + 1)
        linearLayoutToFillHeader.addView(etThing)

        // add button to add datapoint
        var btnAddDatapoint = Button(this)
        btnAddDatapoint.text = "+ " + (base + 0 * 10 + 2).toString()
        btnAddDatapoint.setId(base + 0 * 10 + 2)
        btnAddDatapoint.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnAddDatapoint.setOnClickListener {
            //todo:write addDataPoint()
            addDataPoint(thing)
        }
        linearLayoutToFillHeader.addView(btnAddDatapoint)

        // add button to clear datapoint details list
        var btnClearDetailsList = Button(this)
        btnClearDetailsList.text = "c" + (base + 0 * 10 + 3).toString()
        btnClearDetailsList.setId(base + 0 * 10 + 3)
        btnClearDetailsList.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnClearDetailsList.setOnClickListener {
            removeViewsFromLLDetailsAndLLDetailsHeaderInDetailsScrollablelist()
        }
        linearLayoutToFillHeader.addView(btnClearDetailsList)


        // add button to DELETE all datapoints (DANGER)
        var btnDeleteAllDatapoint = Button(this)
        btnDeleteAllDatapoint.text = "D" + (base + 0 * 10 + 4).toString()
        btnDeleteAllDatapoint.setId(base + 0 * 10 + 4)
        btnDeleteAllDatapoint.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        btnDeleteAllDatapoint.setOnClickListener {
            DANGERdelteAllDatapointsFromThingAndRemoveCorrespondingViewsInDetailsArea(thing)
        }
        linearLayoutToFillHeader.addView(btnDeleteAllDatapoint)

        //HEADER ENDE
    }

    //makeAddNewDatapointAreaVisible

    private fun addDataPoint(thing: Things) {
        var addNewDatapointContainerToHideOrShow = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.addNewDatapointContainer)
        addNewDatapointContainerToHideOrShow.visibility = View.GONE
        // Eingabe elemente Sichtbar machen

        when (thing.datenliste){

        }
        thing.datenliste.add(DatenpunktInt(thing.name,thing.datenliste[thing.datenliste.size-1].id+1, LocalDateTime.now(),
            LocalDateTime.now(),1))

        refreshDatapointsDetailsScrollable(thing)
    }

    private fun DANGERdelteAllDatapointsFromThingAndRemoveCorrespondingViewsInDetailsArea(thing: Things) {
        thing.datenliste.clear()
        //thing.datenliste.removeIf { item -> item.id > 0 }
        removeViewsFromLLDetailsAndLLDetailsHeaderInDetailsScrollablelist()
    }

    private fun removeViewsFromLLDetailsAndLLDetailsHeaderInDetailsScrollablelist() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val linearLayoutToClear = findViewById<LinearLayout>(R.id.llDetails)
        linearLayoutToClear.removeAllViews()
        val linearLayoutToClear2 = findViewById<LinearLayout>(R.id.llDetailsHeader)
        linearLayoutToClear2.removeAllViews()
    }

    private fun switchElementVisibility(id: Int) {
        var elementToMakeVisible = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(id)
        if  (elementToMakeVisible.visibility == View.VISIBLE) {
            elementToMakeVisible.visibility = View.GONE
        } else {
            elementToMakeVisible.visibility = View.VISIBLE
        }
    }

    //OLD STUFF

    //OLD
    private fun removeOneSpecificViewGroupFromThingLL(idParamToDelte: Int) {
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

    //OLD and umständlich
    private fun writeButtonIdStringToetOutputSoThatRemoveOneSpecificKnowsWhatToRemove(btn: Button) {

        // funktion für klicken der geaddeden buttons, macht toast and füllt output textfield
        //OKAY("not implemented") //To change body of created functions use File | Settings | File Templates.
        //TODO("war hilfsbtn, sollte funktion bekommen entweder delete ")
        val editTextOutput = findViewById<EditText>(R.id.et2)
        editTextOutput.setText(btn.id.toString())

        Toast.makeText(this@MainActivity,  R.string.welcome_message, Toast.LENGTH_LONG).show()
    }

    //OLD
    private fun removeAllViewsFromThingsLL() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val linearLayout = findViewById<LinearLayout>(R.id.llThings)
        linearLayout?.removeAllViews()
    }

    //BASIC STUF

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
        var datapointList1 = mutableListOf<daten>(
            DatenpunktInt("things1",12, LocalDateTime.now(), LocalDateTime.now(),11),
            DatenpunktInt("things1",22, LocalDateTime.now(), LocalDateTime.now(),12),
            DatenpunktInt("things1",32, LocalDateTime.now(), LocalDateTime.now(),13),
            DatenpunktInt("things1",42, LocalDateTime.now(), LocalDateTime.now(),14)
        )
        var dummyThing1: Things = Things("t1_kg",(1000*Math.random()).toInt(), LocalDateTime.now(), LocalDateTime.now(),datapointList1)

        var datapointList2 = mutableListOf<daten>(
            DatenpunktInt("things2",21, LocalDateTime.now(), LocalDateTime.now(),21),
            DatenpunktInt("things2",22, LocalDateTime.now(), LocalDateTime.now(),22),
            DatenpunktInt("things2",23, LocalDateTime.now(), LocalDateTime.now(),23),
            DatenpunktInt("things2",24, LocalDateTime.now(), LocalDateTime.now(),24)
        )
        var dummyThing2: Things = Things("t2_cm",(1000*Math.random()).toInt(), LocalDateTime.now(), LocalDateTime.now(),datapointList2)

        listOfThings = mutableListOf<Things>(dummyThing1,dummyThing2)
        //var n = listOfThings.size
    }

    private fun onCreateClean() {
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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

