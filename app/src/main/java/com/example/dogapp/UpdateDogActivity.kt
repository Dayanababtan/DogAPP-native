package com.example.dogapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ComponentActivity

@SuppressLint("RestrictedApi")
class UpdateDogActivity : ComponentActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var activityEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var updateButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dog)

        nameEditText = findViewById(R.id.nameEditText)
        breedEditText = findViewById(R.id.breedEditText)
        activityEditText = findViewById(R.id.activityEditText)
        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)
        updateButton = findViewById(R.id.updateButton)

        updateButton.setOnClickListener {
            updateDog()
        }
    }

    private fun updateDog() {
        val name = nameEditText.text.toString()

        // Găsește indexul câinelui cu numele specificat
        val dogIndex = newArrayList.indexOfFirst { it.name.equals(name, ignoreCase = true) }

        if (dogIndex != -1) {
            // Actualizează detaliile câinelui
            val breed = breedEditText.text.toString()
            val activity = activityEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toIntOrNull() ?: 0

            val updatedDog = newArrayList[dogIndex].apply {
                this.breed = breed
                this.activity = activity
                this.age = age
                this.weight = weight
            }

            // Trimite câinele actualizat și indexul înapoi în `MainActivity`
            val resultIntent = Intent().apply {
                putExtra("updatedDog", updatedDog)
                putExtra("dogIndex", dogIndex)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            // Dacă nu există câine cu numele specificat
            Toast.makeText(this, "Dog not found", Toast.LENGTH_SHORT).show()
        }
    }
}