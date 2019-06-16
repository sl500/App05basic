package com.example.app05basic

interface thingsToTrack : daten {
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