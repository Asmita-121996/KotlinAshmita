package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var preference: SharedPreference
    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout


    private lateinit var et_user_name: TextInputEditText
    private lateinit var et_user_mail: TextInputEditText
    private lateinit var et_password: TextInputEditText
    private lateinit var btn_submit: Button
    private lateinit var txt_link: TextView
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var inputValidation: InputValidation
    val CUSTOM_PREF_NAME = "User_data"
    var Name = ""
    var Email = ""
    var Password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preference = SharedPreference(this)


        // get reference to all views


        // hiding the action bar
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        var name = preference.getValueString("name");
        var email = preference.getValueString("email");
        var password = preference.getValueString("password");

        et_user_name.setText(name)
        et_user_mail.setText(email)
        et_password.setText(password)


        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()


    }

    private fun initViews() {

        textInputLayoutName = findViewById<View>(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutPassword =
            findViewById<View>(R.id.textInputLayoutPassword) as TextInputLayout

        et_user_name = findViewById(R.id.textInputEditTextName) as TextInputEditText
        et_user_mail = findViewById(R.id.textInputEditTextEmail) as TextInputEditText
        et_password = findViewById(R.id.textInputEditTextPassword) as TextInputEditText
        btn_submit = findViewById(R.id.appCompatButtonLogin) as Button
        txt_link = findViewById(R.id.textViewLinkRegister) as TextView


    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {

        appCompatButtonLogin!!.setOnClickListener(this)
        textViewLinkRegister!!.setOnClickListener(this)
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {

        databaseHelper = DatabaseHelper(this)
        inputValidation = InputValidation(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite() {


        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName!!, textInputLayoutName!!, getString(R.string.error_message_name))) {
            return
        }

        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, textInputLayoutPassword!!, getString(R.string.error_message_password))) {
            return
        }



        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' }))
        {

            preference.save("name", textInputEditTextName.text.toString().trim())
            preference.save("email", textInputEditTextEmail.text.toString().trim())
            preference.save("password", textInputEditTextPassword.text.toString().trim())


            val accountsIntent = Intent(this, DemonstrationActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            startActivity(accountsIntent)


        }
        else {

            Snackbar.make(
                nestedScrollView!!,
                getString(R.string.error_valid_email_password),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }


}

