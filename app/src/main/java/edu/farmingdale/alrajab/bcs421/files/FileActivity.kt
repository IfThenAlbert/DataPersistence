package edu.farmingdale.alrajab.bcs421.files

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.farmingdale.alrajab.bcs421.MainActivity
import edu.farmingdale.alrajab.bcs421.database.NameRepository
import edu.farmingdale.alrajab.bcs421.database.User
import edu.farmingdale.alrajab.bcs421.databinding.ActivityFileBinding
import java.io.PrintWriter

class FileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFileBinding
    private lateinit var dbHelper: NameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileBinding.inflate(layoutInflater )
        setContentView(binding.root)

        dbHelper= NameRepository.getInstance(this)

        binding.writeToFile.setOnClickListener { writeToInternalFile() }
        binding.readFromFile.setOnClickListener { readFromInternalFile() }

        binding.goBackBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun readFromInternalFile() {
        val inputStream = openFileInput("myfile")
        val reader = inputStream.bufferedReader()
        val stringBuilder = StringBuilder()
        val lineSeparator = System.getProperty("line.separator")

        // Append each task to stringBuilder
        reader.forEachLine { stringBuilder.append(it).append(lineSeparator) }

        // store data of the file into the db
        binding.textOfFile.text = stringBuilder.toString()
        dbHelper.addUser(User("rfdb","data:"+stringBuilder.toString()))
    }

    private fun writeToInternalFile() {
        val outputStream = openFileOutput("myfile", MODE_PRIVATE)
        val writer = PrintWriter(outputStream)

        // Write each task on a separate line
        writer.println(binding.enterValue.text)

        writer.close()
    }

}