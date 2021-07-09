package com.marcoscampos.assistenttest

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marcoscampos.assistenttest.FeatureActivity.Companion.openFeature

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = findViewById<RecyclerView>(R.id.list)
        list.addItemDecoration(ItemDecoration())
        list.adapter = Adapter()

        val data = intent.data
        val host = data?.host
        val query = data?.getQueryParameter("codigo")

        when (host) {
            "pagar" -> openFeature(this, feats[0])
            "verfatura" -> openFeature(this, feats[1])
            "segundavia" -> openFeature(this, feats[2])
            "bloquear" -> openFeature(this, feats[3])
        }
        Log.v("action", "$host $query")
    }

    class ItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect,
                                    view: View,
                                    parent: RecyclerView,
                                    state: RecyclerView.State) {
            outRect.top = 8
        }
    }

    class Adapter : RecyclerView.Adapter<Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false))

        override fun onBindViewHolder(holder: Holder, position: Int) =
                holder.setData(feats[position])

        override fun getItemCount(): Int = feats.size
    }

    class Holder(private val view: View) : ViewHolder(view) {

        fun setData(feature: String) {
            view.findViewById<TextView>(R.id.item_text).text = feature
            view.setOnClickListener {
                openFeature(view.context, feature)
            }
        }
    }

    companion object {
        val feats = arrayOf("pagar", "ver fatura", "segunda via", "bloquear")
    }
}