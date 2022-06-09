package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val firstName=binding.etName.text.toString()
            val lastName=binding.etLast.text.toString()
            val age=binding.etAge.text.toString().toInt()
            val userName=binding.etUser.text.toString()

            //database = Firebase.database.getReference("Users")
            database=FirebaseDatabase.getInstance().getReference("Users")

            val User=User(firstName,lastName,age,userName)
            database.child(userName).setValue(User).addOnSuccessListener {
                binding.etName.text.clear()
                binding.etLast.text.clear()
                binding.etAge.text.clear()
                binding.etUser.text.clear()

                Toast.makeText(this,"Successfull",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }
        }
    }
}