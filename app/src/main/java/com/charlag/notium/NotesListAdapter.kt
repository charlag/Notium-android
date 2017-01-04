package com.charlag.notium

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView

/**
 * Created by charlag on 25/12/2016.
 */

class NotesListAdapter(private val notes: List<Note>, val clickListener: ClickListener) :
        RecyclerView.Adapter<NotesListAdapter.Holder>() {

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.card_note, parent, false)
        return Holder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.textView?.text = notes[position].content
    }

    class Holder(view: View, private val clickListener: ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var textView: TextView = view.findViewById(R.id.note_text) as TextView

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            clickListener.onListItemClick(adapterPosition)
        }
    }

    interface ClickListener {
        fun onListItemClick(position: Int)
    }
}