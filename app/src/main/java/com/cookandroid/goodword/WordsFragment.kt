package com.cookandroid.goodword

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cookandroid.goodword.databinding.FragmentWordsBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class WordsFragment: Fragment(R.layout.fragment_words) , AdapterView.OnItemClickListener{

    private var binding: FragmentWordsBinding? = null
    //private var arrayList:ArrayList<WordsItem> ? = null
    private var gridView:GridView ? = null
    //private var wordsAdapters:WordsAdapter ? = null
    private lateinit var wordsAdapter: WordsAdapter
    private lateinit var wordsDB: DatabaseReference
    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }

    private var wordsList = ArrayList<WordsItem>()
    private val listener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            var wordsModel = snapshot.getValue(WordsItem::class.java)
            wordsModel ?: return

            wordsList.add(wordsModel)
            wordsAdapter = WordsAdapter(context, wordsList!!)
            gridView?.adapter = wordsAdapter

        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentWordsBinding = FragmentWordsBinding.bind(view)
        binding = fragmentWordsBinding

        wordsList.clear()
        wordsDB = Firebase.database.reference.child("Words")

        /*wordsDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    var wordsModel = snapshot.getValue(WordsItem::class.java)
                    wordsModel ?: return

                    wordsList.add(wordsModel)
                }
                wordsAdapters = WordsAdapter(context, wordsList!!)
            }
        })*/
        gridView = view.findViewById(R.id.gridViewImages)
        //arrayList = ArrayList()
        //arrayList = setData()
        wordsDB.addChildEventListener(listener)
        //wordsAdapter = WordsAdapter(context, wordsList)
        //gridView?.adapter = wordsAdapter
        gridView?.onItemClickListener = this


    }

    /*private fun setData() : ArrayList<WordsItem>{
        var arrayList:ArrayList<WordsItem> = ArrayList()

        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))
        arrayList.add(WordsItem(R.drawable.sample_img, "sample text"))

        return arrayList
    }*/

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var items:WordsItem = wordsList!!.get(p2)
        Toast.makeText(context, items.name, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()

        wordsDB.removeEventListener(listener)
    }
}