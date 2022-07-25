package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

   var tvSignUp:TextView?=null
    var rgGender: RadioGroup?=null
    lateinit var rb: RadioButton
    lateinit var btSignUp: Button
    lateinit var etFullName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var tvDateOfBirth: TextView
    var isFromMainActivity=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        tvSignUp=findViewById(R.id.signUp)
        rgGender = findViewById(R.id.radioGroupGender)
        btSignUp=findViewById(R.id.create)
        etFullName=findViewById(R.id.etName)
        etEmail=findViewById(R.id.etEmailAddress)
        etPassword=findViewById(R.id.etPassword)
        tvDateOfBirth=findViewById(R.id.tvDate)
        isFromMainActivity= intent.getBooleanExtra("isFromMain",false)
        handleUIFlow()
    }

    private fun handleUIFlow()
    {
        if (isFromMainActivity)
        {
             tvSignUp?.visibility  =View.GONE
             btSignUp.text="Update"
        }
    }

    fun clickOnDateOfBirth(view: View) {
        val calendar=Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this@SignupActivity, DatePickerDialog.OnDateSetListener
        { view, year, monthOfYear, dayOfMonth ->

            tvDateOfBirth.setText("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)

        }, year, month, day)

        datePickerDialog.show()
    }
    private fun checkName(){
        val name=etFullName.text.toString()
        if (name.isEmpty()|| name.length<3)
        {
            etFullName.error="Enter your name"
        }
    }
    private fun checkEmail(){
        val mail=etEmail.text.toString()
        if (!mail.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(mail).matches()){

        }
        else {
            etEmail.error="Enter correct email"
        }
    }
    private fun checkPassword(){
        val cpassword=etPassword.text.toString()
        if (cpassword.isEmpty()){
            etPassword.error="Password Required"
        }
    }





    fun buttonClick(view: View) {
        // checking mail , password and name
        checkName()
        checkEmail()
        checkPassword()


        val intSelectButton: Int = rgGender!!.checkedRadioButtonId
        rb = findViewById(intSelectButton)

        //    Button after creating
        Toast.makeText(baseContext, btSignUp.text, Toast.LENGTH_SHORT).show()

        //   val name=etFullName.text.toString()
        //   val mail=etEmail.text.toString()
        // Toast.makeText(applicationContext, "Created", Toast.LENGTH_SHORT).show()
        if(isFromMainActivity){
            finish()
        }
        else{
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        }
    }
}
