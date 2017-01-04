package com.charlag.notium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NoteActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmen_note)

        textView = findViewById(R.id.note_full_text) as TextView

        fill()
    }

    fun fill() {
        val note: Note = intent.getParcelableExtra(getString(R.string.extra_note_text))
        textView.text = note.content
    }
}
