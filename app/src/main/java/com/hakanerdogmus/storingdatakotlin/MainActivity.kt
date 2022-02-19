package com.hakanerdogmus.storingdatakotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hakanerdogmus.storingdatakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var myAge : Int? = null
    var ageFromPreferences : Int? = null
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //küçük boyutlarda kayıt yapmak için kullandığımız komut.
        //her bir kaydetme için ayrı ayrı işlem yapmamız gerekiyor
        //bu yüzden verimli bir yöntem değil.
        // büyük boyutta kayıt için SQLite kullanılır.

        //SharedPreference Initialize
        sharedPreferences = this.getSharedPreferences("com.hakanerdogmus.storingdatakotlin", Context.MODE_PRIVATE)
        ageFromPreferences = sharedPreferences.getInt("age", 0)
        if (ageFromPreferences == 0){
            binding.textView.text = "Your Age: "
        }else{
            binding.textView.text = "Your Age: $ageFromPreferences"
        }

    }

    fun saveButton(view : View){

       myAge = binding.ageText.text.toString().toIntOrNull()
        if (myAge != null){
            binding.textView.text = "Your Age: ${myAge}"
            sharedPreferences.edit().putInt("age", myAge!!).apply()
        }

    }

    fun deleteButton(view : View){
        ageFromPreferences = sharedPreferences.getInt("age", 0)
        if (ageFromPreferences != 0){
            sharedPreferences.edit().remove("age").apply()
            binding.textView.text = "Your Age: "
        }

    }


}