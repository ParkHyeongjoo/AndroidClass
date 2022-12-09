package com.example.wordle

import android.content.Context
import android.graphics.Color

import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(val context: Context, val gameList: ArrayList<GameVO>, val answer: String) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etGame1: EditText
        val etGame2: EditText
        val etGame3: EditText
        val etGame4: EditText
        val etGame5: EditText

        init {
            etGame1 = itemView.findViewById(R.id.etGame1)
            etGame2 = itemView.findViewById(R.id.etGame2)
            etGame3 = itemView.findViewById(R.id.etGame3)
            etGame4 = itemView.findViewById(R.id.etGame4)
            etGame5 = itemView.findViewById(R.id.etGame5)

            var etList = ArrayList<EditText>()
            etList.add(etGame1)
            etList.add(etGame2)
            etList.add(etGame3)
            etList.add(etGame4)
            etList.add(etGame5)


            for (i in 0 until 4) {
                etList[i].setOnKeyListener(object : OnKeyListener {
                    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                        if (p2?.action == KeyEvent.ACTION_DOWN) {
                            etList[i + 1].requestFocus()
                        }
                        return false
                    }
                })
            }
            etGame5.setOnKeyListener(object : OnKeyListener {
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if (p1 == 66 && p2?.action == KeyEvent.ACTION_UP) {
                        Log.d("onkey", "엔터눌러짐")
                        checkAnswer(answer, etList)
                        disableEditText(etList)
                    }
                    return false
                }
            })
        }
    }
    fun enableEditText(etList: ArrayList<EditText>) {
        for (i in 0 until etList.size) {
            etList[i].isEnabled = true
        }
    }

    fun disableEditText(etList: ArrayList<EditText>) {
        for (i in 0 until etList.size) {
            etList[i].isEnabled = false
        }
    }

    fun checkAnswer(answer: String, etList: ArrayList<EditText>) {
        // a p p l e
        // a l b u m
        // 초 노 회 회 회

        for (i in 0 until etList.size) {
            val answerChar: Char = answer.get(i)
            val etChar: Char = etList.get(i).text.toString().single()

            if (answerChar == etChar) {
                etList.get(i).setBackgroundColor(Color.parseColor("green"))
            } else {
                var check = true

                for (j in 0 until etList.size) {
                    if (etChar == answer.get(j)) {
                        etList.get(i).setBackgroundColor(Color.parseColor("yellow"))
                        check = false
                    }
                }
                if (check) {
                    etList.get(i).setBackgroundColor(Color.parseColor("gray"))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.game_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etGame1.setText(gameList[position].word1)
        holder.etGame2.setText(gameList[position].word2)
        holder.etGame3.setText(gameList[position].word3)
        holder.etGame4.setText(gameList[position].word4)
        holder.etGame5.setText(gameList[position].word5)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }
}