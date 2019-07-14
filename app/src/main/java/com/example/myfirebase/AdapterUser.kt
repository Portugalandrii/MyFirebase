package com.example.myfirebase

import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class AdapterUser(var userList: MutableList<User>?, var context: Context, var reference: DatabaseReference?) :
    RecyclerView.Adapter<AdapterUser.ViewHolder>(){
//    View.OnCreateContextMenuListener {
//
//    override fun onCreateContextMenu(
//        menuContext: ContextMenu?,
//        p1: View?,
//        p2: ContextMenu.ContextMenuInfo?
//    ) {
//        val Edit = menuContext!!.add(Menu.NONE, 1, 1, "Edit")
//        val Delete = menuContext.add(Menu.NONE, 2, 2, "Delete")
//        Edit.setOnMenuItemClickListener(Edit)
//        Delete.setOnMenuItemClickListener(Delete)
//
//    }
//
//    private fun MenuItem.setOnMenuItemClickListener(edit: MenuItem?) {
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUser.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        if (userList != null) return userList?.size!!
        else return 0
    }

    override fun onBindViewHolder(vh: AdapterUser.ViewHolder, posit: Int) {

        var userL = userList?.get(posit)
        vh?.name?.text = "Имя: " + userList?.get(posit)?.name
        vh?.age?.text = "Возраст: " + userList?.get(posit)?.age
        //vh?.item.setOnCreateContextMenuListener(this)
        vh?.item.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Title")
            builder.setMessage("Delete")
            builder.setPositiveButton("Yes"){
                    dialog, which ->

                val user =  reference?.child(userList?.get(posit)?.key!!)
                user?.removeValue()


            }
            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.recycler_name)
        val age = itemView.findViewById<TextView>(R.id.recycler_age)
        var item: LinearLayout = itemView.findViewById(R.id.item)

    }
}



