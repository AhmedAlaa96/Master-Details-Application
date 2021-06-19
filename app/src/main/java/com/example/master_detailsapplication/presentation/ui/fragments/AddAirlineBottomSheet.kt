package com.example.master_detailsapplication.presentation.ui.fragments


import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.example.master_detailsapplication.R
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.databinding.AddAirlineBottomSheetBinding
import com.example.master_detailsapplication.infrastructure.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("DEPRECATION")
class AddAirlineBottomSheet(private val onConfirmPressed: (airline: Airline) -> Unit)
    : BottomSheetDialogFragment() {
    companion object {

        const val TAG = "AddAirLineBottomSheetDialogFragment"

    }

    private lateinit var binding: AddAirlineBottomSheetBinding



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

        binding = view?.let { AddAirlineBottomSheetBinding.bind(it) }!!


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



        binding.confirmBtn.setOnClickListener{

            val airline = Airline("",
                binding.nameEditText.text.toString().trim(),
                binding.countryEditText.text.toString().trim(),
               "",
                binding.sloganEditText.text.toString().trim(),
                binding.headquartersEditText.text.toString().trim(),
                binding.websiteEditText.text.toString().trim(),
                binding.establishedEditText.text.toString().trim(),
            )
            onConfirmPressed(airline)
            dismiss()
        }
        binding.cancelBtn.setOnClickListener { dismiss() }

        binding.nameEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.countryEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.sloganEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.headquartersEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.websiteEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.establishedEditText.addTextChangedListener {
            checkEditTexts()
        }
        binding.nameEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                value = true
                checkEditTexts()
                binding.sloganEditText.requestFocus()
            }
            return@setOnEditorActionListener value
        }
        binding.sloganEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                value = true
                checkEditTexts()
                binding.countryEditText.requestFocus()
            }
            return@setOnEditorActionListener value
        }

        binding.countryEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                value = true
                checkEditTexts()
                binding.headquartersEditText.requestFocus()
            }
            return@setOnEditorActionListener value
        }



        binding.headquartersEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                value = true
                checkEditTexts()
                binding.websiteEditText.requestFocus()
            }
            return@setOnEditorActionListener value
        }

        binding.websiteEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                value = true
                checkEditTexts()
                binding.establishedEditText.requestFocus()
            }
            return@setOnEditorActionListener value
        }
        binding.establishedEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_DONE){
                value = true
                clearFocus()
                checkEditTexts()
            }
            return@setOnEditorActionListener value
        }


    }


    private fun checkEditTexts(){
        binding.confirmBtn.isEnabled = binding.nameEditText.text.toString().trim().isNotEmpty() &&
                binding.countryEditText.text.toString().trim().isNotEmpty() &&
                binding.sloganEditText.text.toString().trim().isNotEmpty() &&
                binding.headquartersEditText.text.toString().trim().isNotEmpty() &&
                binding.websiteEditText.text.toString().trim().isNotEmpty() &&
                binding.establishedEditText.text.toString().trim().isNotEmpty()
    }

    private fun clearFocus(){
        Utils.hideKeyboard(binding.root.context,binding.nameEditText)
        Utils.hideKeyboard(binding.root.context,binding.countryEditText)
        Utils.hideKeyboard(binding.root.context,binding.sloganEditText)
        Utils.hideKeyboard(binding.root.context,binding.headquartersEditText)
        Utils.hideKeyboard(binding.root.context,binding.websiteEditText)
        Utils.hideKeyboard(binding.root.context,binding.establishedEditText)
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