package com.example.kotlinfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
/**
 * WorkoutsFragment - Log, view and manage workouts.
 */
class WorkoutsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }
}
