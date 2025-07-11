package com.example.kotlinfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
/**
 * NutritionFragment - Log and review meals, track calories/nutrients.
 */
class NutritionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }
}
