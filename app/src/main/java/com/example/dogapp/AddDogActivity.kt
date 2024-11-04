package com.example.dogapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class AddDogActivity : ComponentActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var activityEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dog)

        nameEditText = findViewById(R.id.nameEditText)
        breedEditText = findViewById(R.id.breedEditText)
        activityEditText = findViewById(R.id.activityEditText)
        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)

        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            addDog()
        }
    }

    private fun addDog() {
        val name = nameEditText.text.toString()
        val breed = breedEditText.text.toString()
        val activity = activityEditText.text.toString()
        val age = ageEditText.text.toString().toIntOrNull() ?: 0
        val weight = weightEditText.text.toString().toIntOrNull() ?: 0

        val newDog = Dogs(
            titleImage = R.drawable.a,
            name = name,
            breed = breed,
            age = age,
            weight = weight,
            activity = activity
        )

        val resultIntent = Intent()
        resultIntent.putExtra("newDog", newDog)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
