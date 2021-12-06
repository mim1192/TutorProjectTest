package com.mim.tutorprojecttest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.mim.tutorprojecttest.adapters.CustomPagerAdapter
import com.mim.tutorprojecttest.api.APIClient
import com.mim.tutorprojecttest.api.APIInterface
import com.mim.tutorprojecttest.classes.Detail
import com.mim.tutorprojecttest.classes.Details
import com.mim.tutorprojecttest.db.DBTables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var view_pager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inIt()
        var db_tables: DBTables? = null
        db_tables = DBTables(this@MainActivity)
        var list = db_tables.retrieveDetails()
        if (list != null && list.size > 0
        ) {


            val adapter = CustomPagerAdapter(
                this@MainActivity,
                list as ArrayList<Detail>?,

                )
            view_pager.setAdapter(adapter)

        } else
            getList()

    }

    private fun getList() {
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage(this@MainActivity.resources.getString(R.string.searching))
        progressDialog.setCancelable(false)
        progressDialog.show()
        val apiInterfaceJava: APIInterface
        apiInterfaceJava = APIClient.getClient().create(APIInterface::class.java)

        val call: Call<Details> =
            apiInterfaceJava.GetList()

        call.enqueue(object : Callback<Details?> {
            override fun onResponse(
                call: Call<Details?>,
                response: Response<Details?>
            ) {
                var db_tables: DBTables? = null
                db_tables = DBTables(this@MainActivity)
                db_tables.insertDetailsComplete(response.body()!!.details as ArrayList<Detail>?)
                progressDialog!!.dismiss()
                val adapter = CustomPagerAdapter(
                    this@MainActivity,
                    db_tables.retrieveDetails() as ArrayList<Detail>?,

                    )
                view_pager.setAdapter(adapter)
            }

            override fun onFailure(
                call: Call<Details?>,
                t: Throwable
            ) {

                progressDialog.dismiss()
            }
        })

    }

    private fun inIt() {
        view_pager = findViewById(R.id.view_pager)


    }
}