package com.example.myfirebase

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast





class MainActivity : AppCompatActivity() {
    // Создаем ссылку на нашу базу данных
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var result : MutableList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        var adapter: AdapterUser = AdapterUser(result,this)
        recyclerView.adapter = adapter
        database = FirebaseDatabase.getInstance()
        reference = database!!.getReference("user")
        val myRef = database!!.getReference("user")

        save.setOnClickListener {
            if (edit_name!=null && edit_age!=null){
               // val database = FirebaseDatabase.getInstance()

                var name = edit_name.text.toString()
                var age = edit_age.text.toString()
                val key: String = myRef.push().key!!
                var user = key?.let { it1 -> User(it1,name, age) }

                myRef!!.child(key).setValue(user)
                //myRef.push().setValue(user)

                edit_name.setText("")
                edit_age.setText("")
            }
        }

        val userListener = object : ChildEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                var user: User = dataSnapshot.getValue(User::class.java)!!
                var index = getItemIndex(user)
                result?.set(index,user)
                adapter.notifyDataSetChanged()
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                var user: User = dataSnapshot.getValue(User::class.java)!!
                result?.add(user)
                adapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                var user: User = dataSnapshot.getValue(User::class.java)!!
                var index = getItemIndex(user)
                result?.removeAt(index)
                adapter.notifyDataSetChanged()
            }

        }
        reference!!.addChildEventListener(userListener)


    }

    private fun getItemIndex(user: User): Int {
        var index = -1
        if (this.result!=null) {
            for (i in this!!.result!!) {
                index++
                if (i.key.equals(user.key))
                break
            }
        }
        return index
    }
}






