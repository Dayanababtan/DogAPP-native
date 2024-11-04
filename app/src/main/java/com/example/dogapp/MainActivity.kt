package com.example.dogapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

public lateinit var newArrayList: ArrayList<Dogs>
class MainActivity : ComponentActivity() {

    private lateinit var newRecyclerview: RecyclerView
    private lateinit var myAdapter: MyAdapter

    private lateinit var imageId: Array<Int>
    private lateinit var name: Array<String>
    private lateinit var age: Array<Int>
    private lateinit var breed: Array<String>
    private lateinit var activity: Array<String>
    private lateinit var weight: Array<Int>

    private companion object {
        private const val ADD_DOG_REQUEST_CODE = 1
        private const val UPDATE_DOG_REQUEST_CODE = 2
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c
        )

        name = arrayOf(
            "Aki",
            "Bruno",
            "Rocky"
        )

        age = arrayOf(
            3,
            5,
            2
        )

        weight = arrayOf(
            40,
            20,
            5
        )

        activity = arrayOf(
            "running",
            "eating",
            "barking"
        )

        breed = arrayOf(
            "akita",
            "beagle",
            "teckel"
        )

        newRecyclerview = findViewById(R.id.recyclerView)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.setHasFixedSize(true)

        newArrayList = arrayListOf<Dogs>()
        myAdapter = MyAdapter(newArrayList)
        newRecyclerview.adapter = myAdapter

        getUserdata()


        val addDogButton: Button = findViewById(R.id.addDogButton)
        addDogButton.setOnClickListener {
            val intent = Intent(this, AddDogActivity::class.java)
            startActivityForResult(intent, ADD_DOG_REQUEST_CODE)
        }

        val updateDogButton: Button = findViewById(R.id.updateDogButton)
        updateDogButton.setOnClickListener {
            var dogIndex = 0
            val intent = Intent(this, UpdateDogActivity::class.java)
            intent.putExtra("dogIndex", dogIndex)
            startActivityForResult(intent, UPDATE_DOG_REQUEST_CODE)
        }


    }

    private fun getUserdata() {
        for (i in imageId.indices) {
            val dogs = Dogs(imageId[i], name[i], breed[i], age[i], weight[i], activity[i])
            newArrayList.add(dogs)
        }
        myAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DOG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newDog: Dogs? = data?.getParcelableExtra("newDog")
            if (newDog != null) {
                newArrayList.add(newDog)
                myAdapter.notifyItemInserted(newArrayList.size - 1) // Notify adapter of the new item
            }
        }else if (requestCode == UPDATE_DOG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedDog: Dogs? = data?.getParcelableExtra("updatedDog")
            val dogIndex = data?.getIntExtra("dogIndex", -1) ?: -1

            if (updatedDog != null && dogIndex != -1) {
                newArrayList[dogIndex] = updatedDog
                myAdapter.notifyItemChanged(dogIndex)
            }
        }
    }
}
