package com.example.master_detailsapplication.presentation.ui.fragments


import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.master_detailsapplication.R
import com.example.master_detailsapplication.databinding.AddAirlineBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("DEPRECATION")
class AddAirlineBottomSheet
    : BottomSheetDialogFragment() {
    companion object {

        const val TAG = "AddAirLineBottomSheetDialogFragment"

    }

    private lateinit var addAirlineBottomSheetBinding: AddAirlineBottomSheetBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.CustomBottomSheetDialogTheme)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.add_airline_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addAirlineBottomSheetBinding = view?.let { AddAirlineBottomSheetBinding.bind(it) }!!


        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior: BottomSheetBehavior<View> = BottomSheetBehavior.from(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = 0
        behavior.expandedOffset = getBottomSheetDialogDefaultHeight()
        behavior.isDraggable= false
        behavior.isHideable = false


        initComponents()



    }



    private fun initComponents(){


        addAirlineBottomSheetBinding.confirmBtn.setOnClickListener{ dismiss() }
        addAirlineBottomSheetBinding.cancelBtn.setOnClickListener { dismiss() }
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 70 / 100
    }
    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay
            .getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}