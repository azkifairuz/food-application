package com.javfairuz.foodapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javfairuz.foodapplication.R


/**
 * A simple [Fragment] subclass.
 * Use the [Text1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Text1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text1, container, false)
    }

}