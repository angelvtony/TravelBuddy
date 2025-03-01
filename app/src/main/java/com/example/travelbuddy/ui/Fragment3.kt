package com.example.travelbuddy.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.travelbuddy.R

class Fragment3: Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.layout_fragment3, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.skipBtn).setOnClickListener(View.OnClickListener {
            navigateToActivity()

        })
        view.findViewById<CardView>(R.id.getStartedCV).setOnClickListener(View.OnClickListener {
            navigateToActivity()
        })
    }
    fun navigateToActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}