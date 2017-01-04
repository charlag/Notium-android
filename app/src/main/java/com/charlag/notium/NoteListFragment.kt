package com.charlag.notium

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by charlag on 02/01/2017.
 */

class NoteListFragment : Fragment(), NotesListAdapter.ClickListener {

    companion object {
        val NOTES_KEY = "NOTES_KEY"

        fun getInstance(notes: List<Note>): NoteListFragment {
            val fragment = NoteListFragment()
            val args = Bundle()
            args.putParcelableArrayList(NOTES_KEY, ArrayList(notes))
            fragment.arguments = args
            return fragment
        }
    }

    var notes: ArrayList<Note>? = null
    var noteSelectedListener: NoteSelectedListener? = null

    lateinit var noteList: RecyclerView
    lateinit var fab: FloatingActionButton

    lateinit var adapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            notes = savedInstanceState.getParcelableArrayList(NOTES_KEY)
        } else {
            notes = arguments.getParcelableArrayList(NOTES_KEY)
        }

        adapter = NotesListAdapter(notes!!, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        noteList = view.findViewById(R.id.notes_list) as RecyclerView
        noteList.adapter = adapter
        noteList.layoutManager = LinearLayoutManager(activity)

        fab = view.findViewById(R.id.fab_add_note) as FloatingActionButton
        fab.setOnClickListener {
            notes?.add(Note("One more note!\nLet's fill some stuff so it will be longer. No, really, let's try to add it, we need it for tests"))
            adapter.notifyItemInserted(notes?.size ?: 0)
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(NOTES_KEY, notes)
    }

    override fun onListItemClick(position: Int) {
        noteSelectedListener?.onNoteSelected(notes!![position],
                noteList.layoutManager.findViewByPosition(position))
    }

    interface NoteSelectedListener {
        fun onNoteSelected(note: Note, view: View)
    }
}
