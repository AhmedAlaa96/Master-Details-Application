package com.example.master_detailsapplication.presentation.ui.activities

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.master_detailsapplication.R
import com.example.master_detailsapplication.data.repositrory.AirlineLocalRepositoryImpl
import com.example.master_detailsapplication.domain.adapters.AirlinesAdapter
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.data.repositrory.AirlineRepositoryImpl
import com.example.master_detailsapplication.data.source.local.AppDatabase
import com.example.master_detailsapplication.databinding.ActivityMainBinding
import com.example.master_detailsapplication.databinding.ContentMainBinding
import com.example.master_detailsapplication.domain.*
import com.example.master_detailsapplication.infrastructure.utils.Utils
import com.example.master_detailsapplication.presentation.ui.fragments.AddAirlineBottomSheet
import com.example.master_detailsapplication.presentation.viewModels.MainViewModel
import com.example.master_detailsapplication.presentation.viewModels.interfaces.MainViewModelInterface

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var contentMainActivityBinding: ContentMainBinding
    private lateinit var airlinesAdapter: AirlinesAdapter
    private lateinit var airlinesList: RecyclerView
    private lateinit var emptyAirlineState: View
    private lateinit var noInternetConnectionState: View
    private lateinit var errorState: View
    private lateinit var mainViewModel: MainViewModelInterface
    private lateinit var airlinesItemsList: MutableList<Airline>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainActivityBinding.root)

        contentMainActivityBinding = mainActivityBinding.contentMain


        initComponents()

        }

    private fun initComponents() {
        airlinesAdapter = AirlinesAdapter()

        airlinesList = contentMainActivityBinding.airlinesList
        emptyAirlineState = contentMainActivityBinding.emptyAirlineState.root
        noInternetConnectionState = contentMainActivityBinding.noInternetConnectionState.root
        errorState = contentMainActivityBinding.errorState.root

        airlinesList.adapter = airlinesAdapter

        airlinesList.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        val airlineDao = AppDatabase.getInstance(applicationContext).airlineDao

        mainViewModel = MainViewModel(
            ViewModelUseCases(
                GetAirlinesUseCase(
                    AirlineRepositoryImpl()
                ),
                CreateAirlineUseCase(
                    AirlineRepositoryImpl()
                ),
                GetLocalAirlinesUseCase(
                    AirlineLocalRepositoryImpl(airlineDao)
                ),
                CreateLocalAirlineUseCase(
                    AirlineLocalRepositoryImpl(airlineDao)
                ),
                DeleteLocalAirlinesUseCase(
                    AirlineLocalRepositoryImpl(airlineDao)
                )
            )
        )

        mainViewModel.getLocalAirlines.observe(this,{
            mainViewModel.loading(false)
            if(it[0] as Boolean){
                airlinesItemsList = (it[1] as List<Airline>).toMutableList()
                airlinesAdapter.setItems(airlinesItemsList)
                if(airlinesItemsList.isEmpty()){
                    airlinesList.visibility = View.GONE
                    emptyAirlineState.visibility = View.GONE
                    noInternetConnectionState.visibility = View.VISIBLE
                    errorState.visibility = View.GONE
                }else{
                    airlinesList.visibility = View.VISIBLE
                    emptyAirlineState.visibility = View.GONE
                    noInternetConnectionState.visibility = View.GONE
                    errorState.visibility = View.GONE
                }
            }else{
                println("ERROR")
                println(it[1])
                airlinesList.visibility = View.GONE
                emptyAirlineState.visibility = View.GONE
                noInternetConnectionState.visibility = View.GONE
                errorState.visibility = View.VISIBLE
            }
        })

        mainViewModel.getAirlines.observe(this,{
            if(it[0] as Boolean){
                mainViewModel.loading(false)
                airlinesList.visibility = View.VISIBLE
                emptyAirlineState.visibility = View.GONE
                noInternetConnectionState.visibility = View.GONE
                errorState.visibility = View.GONE
                airlinesItemsList = (it[1] as List<Airline>).toMutableList()
                airlinesAdapter.setItems(airlinesItemsList)
                mainViewModel.createLocalAirline(airlinesItemsList)
            }else{
                println("ERROR")
                println(it[1])
                mainViewModel.getLocalAirLinesList()
            }
        })

        mainViewModel.isLoading.observe(this,{
            runOnUiThread {
                if(it) {
                    mainActivityBinding.loadingIndicator.visibility = View.VISIBLE
                    airlinesList.visibility = View.GONE
                    emptyAirlineState.visibility = View.GONE
                    noInternetConnectionState.visibility = View.GONE
                    errorState.visibility = View.GONE
                }
                else{
                    mainActivityBinding.loadingIndicator.visibility  = View.GONE
                }
            }
        })

        mainViewModel.createAirline.observe(this,{
            mainViewModel.loading(false)
            airlinesList.visibility = View.VISIBLE
            emptyAirlineState.visibility = View.GONE
            noInternetConnectionState.visibility = View.GONE
            errorState.visibility = View.GONE
            if(it[0] as Boolean){
                airlinesAdapter.addItem(it[1] as Airline)
            }else{
                if(it[1].toString() == getString(R.string.BE_internet_error)){
                    Utils.noInternetConnectionDialog(this)
                }else{
                    Utils.errorCustomDialog(this)
                }
                println("ERROR")
                println(it[1])
            }
        })


        contentMainActivityBinding.searchBtn.setOnClickListener {
            contentMainActivityBinding.searchEditText.clearFocus()
            Utils.hideKeyboard(applicationContext,contentMainActivityBinding.searchEditText)
            searchByName() }

        contentMainActivityBinding.searchEditText.addTextChangedListener{
            if(it.toString().trim().isEmpty()) {
                searchByName()
            }
        }

        contentMainActivityBinding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            var value = false
            if(actionId == EditorInfo.IME_ACTION_DONE){
                value = true
                contentMainActivityBinding.searchEditText.clearFocus()
                Utils.hideKeyboard(applicationContext,contentMainActivityBinding.searchEditText)
                searchByName()
            }
            return@setOnEditorActionListener value
        }
        mainActivityBinding.fab.setOnClickListener { AddAirlineBottomSheet(::onConfirmPressed).show(supportFragmentManager,AddAirlineBottomSheet.TAG) }


        contentMainActivityBinding.noInternetConnectionState.retryBtn.setOnClickListener {
            mainViewModel.loading(true)
            mainViewModel.getAirLinesList()
        }
        contentMainActivityBinding.errorState.retryBtn.setOnClickListener {
            mainViewModel.loading(true)
            mainViewModel.getAirLinesList()
        }
    }

    private fun searchByName() {

        if(contentMainActivityBinding.searchEditText.text.toString().trim().isNotEmpty()){
            val searchValue = contentMainActivityBinding.searchEditText.text.toString().trim()
            val tempItems = mutableListOf<Airline>()
            for (item in airlinesItemsList){
                if((!item.name.isNullOrEmpty() && item.name.contains(searchValue,true))
                    || (!item.id.isNullOrEmpty() && item.id.contains(searchValue,true) )
                    || (!item.country.isNullOrEmpty() && item.country.contains(searchValue,true))){
                    tempItems.add(item)
                }
            }

            if(tempItems.isEmpty()){
                airlinesList.visibility = View.GONE
                emptyAirlineState.visibility = View.VISIBLE
            }else{
                airlinesList.visibility = View.VISIBLE
                emptyAirlineState.visibility = View.GONE
            }
            airlinesAdapter.setItems(tempItems)
        }
        else{
            airlinesList.visibility = View.VISIBLE
            emptyAirlineState.visibility = View.GONE
            airlinesAdapter.setItems(airlinesItemsList)
        }
    }

    private fun onConfirmPressed(airline: Airline){
        airline.id = (airlinesAdapter.itemCount + 1).toString()
        mainViewModel.createAirline(airline)
        mainViewModel.loading(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.dispose()
    }


}