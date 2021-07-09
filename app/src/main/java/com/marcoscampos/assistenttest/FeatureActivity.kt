package com.marcoscampos.assistenttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature_main)
        val textFeature = findViewById<TextView>(R.id.tx_feature)
        intent.getStringExtra("feature").let {
            textFeature.text = it
        }
    }

    companion object {
        fun openFeature(context: Context, feature: String) {
            val data = Intent(context, FeatureActivity::class.java)
            data.putExtra("feature", feature)
            context.startActivity(data)
        }
    }
}