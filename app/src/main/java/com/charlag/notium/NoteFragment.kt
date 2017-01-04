package com.charlag.notium

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by charlag on 02/01/2017.
 */

class NoteFragment : Fragment() {

    companion object {
        val NOTE_KEY = "NOTE_KEY"

        fun newInstance(note: Note): NoteFragment {
            val args = Bundle()
            args.putParcelable(NOTE_KEY, note)
            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var note: Note? = null

    lateinit var contentView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = arguments.get(NOTE_KEY) as Note
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragmen_note, container, false)
        contentView = view.findViewById(R.id.note_full_text) as TextView
        contentView.text = note?.content
        return view
    }
}