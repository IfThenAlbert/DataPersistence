package edu.farmingdale.alrajab.bcs421

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SharedScreen : AppCompatActivity() {
    lateinit var inpFirst:EditText
    lateinit var inpLast:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_screen)

        inpFirst = findViewById(R.id.inp_first_name)
        inpLast = findViewById(R.id.inp_last_name)
    }

    fun onButtonRead(v: View) {
        val sh =this.getSharedPreferences(getString(R.string.app_share_pref),MODE_PRIVATE)
        var resView:TextView = findViewById(R.id.txtSharedRes)
        val name:String = sh.getString("fname","John") + ""+ sh.getString("lname","Doe")
        resView.setText("LAST ENTERED NAME WAS: " + name)
    }

    fun onButtonSave(v: View) {
        val sh =this.getSharedPreferences(getString(R.string.app_share_pref),MODE_PRIVATE)
        //var inputName:String = inpFirst.text.toString() + "" +  inpLast.text.toString()
        sh.edit().putString("fname",inpFirst.text.toString()).apply()
        sh.edit().putString("lname",inpLast.text.toString()).apply()
        Toast.makeText(applicationContext,"SAVED!",Toast.LENGTH_LONG).show()

    }

    fun onButtonUpdate(v: View) {
        val sh =this.getSharedPreferences(getString(R.string.app_share_pref),MODE_PRIVATE)
        sh.edit().putString("fname",inpFirst.text.toString()).apply()
        sh.edit().putString("lname",inpLast.text.toString()).apply()
        Toast.makeText(applicationContext,"SAVED!",Toast.LENGTH_LONG).show()
    }
}