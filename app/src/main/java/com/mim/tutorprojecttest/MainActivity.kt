package com.mim.tutorprojecttest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mim.tutorprojecttest.api.APIClient
import com.mim.tutorprojecttest.api.APIInterface
import com.mim.tutorprojecttest.classes.Details
import com.mim.tutorprojecttest.db.DBTables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inIt()


    }

    private fun getList() {
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage(this@MainActivity.resources.getString(R.string.searching))
        progressDialog.setCancelable(false)
        progressDialog.show()
        val apiInterfaceJava: APIInterface
        apiInterfaceJava = APIClient.getClient().create(APIInterface::class.java)

        val call: Call<ArrayList<Details?>> =
            apiInterfaceJava.GetList()

        call.enqueue(object : Callback<ArrayList<Details?>?> {
            override fun onResponse(
                call: Call<ArrayList<Details?>?>,
                response: Response<ArrayList<Details?>?>
            ) {
                var db_tables: DBTables? = null
                db_tables = DBTables(this@MainActivity)
                db_tables.insertDetails(response.body())
                progressDialog!!.dismiss()
            }

            override fun onFailure(
                call: Call<ArrayList<Details?>?>,
                t: Throwable
            ) {

                progressDialog.dismiss()
            }
        })

    }

    private fun inIt() {

    }
}