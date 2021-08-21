package com.example.myapplication

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UsersListActivity : AppCompatActivity() {

    private val activity = this@UsersListActivity
    private lateinit var textViewName: TextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<Users>
    private lateinit var usersRecyclerAdapter: UserRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        databaseHelper = DatabaseHelper(activity)

        supportActionBar!!.title = ""
        initViews()
        initObjects()
        StringFunction()
        BooleanFunction();

    }


    fun StringFunction() {
        val Fullname: String                             // defining a variable
        val FirstName: String                             // defining a variable
        val Surname: String                             // defining a variable
        Fullname = "Asmita VasanthaKumar Thodan"        // Assigning a value to it
        FirstName = " ASMITA  "                         // Assigning a value to it
        Surname = " Thodan House"                                 // Assigning a value to it

        Log.e("Asmita==>", "StringFunction ==========")
        Log.e("Asmita==>",""+ "Your Name is : " + Fullname)
        Log.e("Asmita==>",""+"Hello!" + FirstName)
        Log.e("Asmita==>",""+"Hey!!" + Surname)


    }

    fun BooleanFunction() {
        val text: Boolean
        text = true
        println("$text")

    }



    /**
     * This method is to initialize views
     */
    private fun initViews() {
        textViewName = findViewById<View>(R.id.textViewName) as TextView
        recyclerViewUsers = findViewById<View>(R.id.recyclerViewUsers) as RecyclerView
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()

        val emailFromIntent = intent.getStringExtra("EMAIL")
        textViewName.text = emailFromIntent

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
        Log.e("Asmita==>", "getDataFromSQLite = " + getDataFromSQLite.toString());


    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Users>>() {

        override fun doInBackground(vararg p0: Void?): List<Users> {

            Log.e("Asmita==>", " ======Background====== ")
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<Users>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)

            Log.e("Asmita==>", "List_Users = " + listUsers.size);


            usersRecyclerAdapter = UserRecyclerAdapter(listUsers)
            val mLayoutManager = LinearLayoutManager(applicationContext)
            recyclerViewUsers.layoutManager = mLayoutManager
            recyclerViewUsers.itemAnimator = DefaultItemAnimator()
            recyclerViewUsers.setHasFixedSize(true)
            recyclerViewUsers.adapter = usersRecyclerAdapter

            for (i in listUsers.indices) {
                Log.e("Asmita==>", " " + listUsers[i])
            }


        }

    }


}