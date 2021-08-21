package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DemonstrationActivity : AppCompatActivity() {


    private lateinit var txt_fullName: TextInputLayout
    private lateinit var txt_fathersName: TextInputLayout
    private lateinit var txt_mothersName: TextInputLayout
    private lateinit var txt_DOB: TextInputLayout
    private lateinit var txt_add: TextInputLayout
    private lateinit var txt_designation: TextInputLayout
    private lateinit var txt_description: TextInputLayout

    private lateinit var ed_fullName: TextInputEditText
    private lateinit var ed_fathersName: TextInputEditText
    private lateinit var ed_MothersName: TextInputEditText
    private lateinit var ed_DOB: TextInputEditText
    private lateinit var ed_add: TextInputEditText
    private lateinit var ed_designation: TextInputEditText
    private lateinit var ed_description: TextInputEditText
    private lateinit var btn_save: Button


    private lateinit var rd_maritalStatus: RadioGroup
    private lateinit var rd_graduation: RadioGroup


    private var FullName: String = ""
    private var FathersName: String = ""
    private var MothersName: String = ""
    private var DOB: String = ""
    private var Address: String = ""
    private var Designation: String = ""
    private var Description: String = ""

    private var GraduationValue: String = ""
    private var MaritalStatusValue: String = ""

    var Graduation_id: Int = 0
    var M_Status_id: Int = 0


    private lateinit var preference: SharedPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo)
        preference = SharedPreference(this)

        intializingViews()


        btn_save.setOnClickListener(View.OnClickListener {

            getInputValues()

        })


        rd_graduation.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val G_radio: RadioButton = findViewById(checkedId)

                Log.e("Asmita==>", "G_radio :: " + G_radio.text)
                Toast.makeText(applicationContext, " On Graduation Radio change : ${G_radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

        rd_maritalStatus.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val M_radio: RadioButton = findViewById(checkedId)

                Log.e("Asmita==>", "M_radio :: " + M_radio.text)
                Toast.makeText(applicationContext, " On MaritalStatus Radio change : ${M_radio.text}",
                    Toast.LENGTH_SHORT).show()
            })







    }

    private fun getInputValues() {

        Graduation_id = rd_graduation.checkedRadioButtonId
        M_Status_id = rd_maritalStatus.checkedRadioButtonId

        Log.e("Asmita==>", "Graduation_id :: " + Graduation_id)
        Log.e("Asmita==>", "M_Status_id :: " + M_Status_id)



        if (ed_fullName.text.toString().trim().isEmpty()) {
            ed_fullName.error = "FullName  is required."

        } else if (ed_fathersName.text.toString().trim().isEmpty()) {
            ed_fathersName.error = "Father's Name is required."

        } else if (ed_MothersName.text.toString().trim().isEmpty()) {
            ed_MothersName.error = "Mother's Name is required."


        } else if (ed_DOB.text.toString().trim().isEmpty()) {
            ed_DOB.error = "Date Of Birth is required."


        } else if (ed_add.text.toString().trim().isEmpty()) {
            ed_add.error = "Address is required."


        } else if (ed_designation.text.toString().trim().isEmpty()) {
            ed_designation.error = "Designation is required."


        } else if (ed_description.text.toString().trim().isEmpty()) {
            ed_description.error = "Description is required."

        } else if (Graduation_id <1) {

            Toast.makeText(
                applicationContext, "Please select your Graduation",
                Toast.LENGTH_SHORT
            ).show()
            return

        } else if (M_Status_id<1) {

            Toast.makeText(
                applicationContext, "Please select your Marital Status",
                Toast.LENGTH_SHORT
            ).show()
            return

        }else{

            val Graduation_Radio: RadioButton = findViewById(Graduation_id)
            val MaritalStatus_Radio: RadioButton = findViewById(M_Status_id)



            FullName = ed_fullName.text.toString().trim()
            FathersName = ed_fathersName.text.toString().trim()
            MothersName = ed_MothersName.text.toString().trim()
            DOB = ed_DOB.text.toString().trim()
            Address = ed_add.text.toString().trim()
            Designation = ed_designation.text.toString().trim()
            Description = ed_description.text.toString().trim()

            MaritalStatusValue = MaritalStatus_Radio.text.toString()
            GraduationValue = Graduation_Radio.text.toString()
            Toast.makeText(
                applicationContext, "Successfully Saved",
                Toast.LENGTH_SHORT
            ).show()



            preference.save("FullName", FullName)
            preference.save("FathersName", FathersName)
            preference.save("MothersName", MothersName)
            preference.save("DOB", DOB)
            preference.save("Address", Address)
            preference.save("Designation", Designation)
            preference.save("Description", Description)

            preference.save("GraduationValue", GraduationValue)
            preference.save("MaritalStatusValue", MaritalStatusValue)

            val fragment = DisplayFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.displayFragment, fragment)
            transaction.commit()

        }



    }

    private fun intializingViews() {


        txt_fullName = findViewById(R.id.txt_fullName) as TextInputLayout
        txt_fathersName = findViewById(R.id.txt_fathersName) as TextInputLayout
        txt_mothersName = findViewById(R.id.txt_mothersName) as TextInputLayout
        txt_DOB = findViewById(R.id.txt_DOB) as TextInputLayout
        txt_add = findViewById(R.id.txt_add) as TextInputLayout
        txt_designation = findViewById(R.id.txt_designation) as TextInputLayout
        txt_description = findViewById(R.id.txt_description) as TextInputLayout



        ed_fullName = findViewById(R.id.ed_fullName) as TextInputEditText
        ed_fathersName = findViewById(R.id.ed_fathersName) as TextInputEditText
        ed_MothersName = findViewById(R.id.ed_MothersName) as TextInputEditText
        ed_add = findViewById(R.id.ed_add) as TextInputEditText
        ed_designation = findViewById(R.id.ed_designation) as TextInputEditText
        ed_description = findViewById(R.id.ed_description) as TextInputEditText
        ed_DOB = findViewById(R.id.ed_DOB) as TextInputEditText
        btn_save = findViewById(R.id.btn_save) as Button


        rd_maritalStatus = findViewById(R.id.rd_maritalStatus) as RadioGroup
        rd_graduation = findViewById(R.id.graduation) as RadioGroup


    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(rd_graduation.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }

}