package com.example.master_detailsapplication.presentation.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.master_detailsapplication.domain.*
import com.example.master_detailsapplication.domain.base.TestingIOSchedulers
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository
import com.example.master_detailsapplication.domain.repository.AirlineRepository
import com.example.master_detailsapplication.presentation.viewModels.interfaces.MainViewModelInterface
import io.reactivex.Single
import org.junit.*
import java.lang.Exception

class MainViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModelInterface
    private lateinit var  liveDataTestUtil: LiveDataTestUtil


    private lateinit var viewModelUseCases: ViewModelUseCasesInterface

    @Before
    fun before(){

        viewModelUseCases = ViewModelUseCasesMock()

        mainViewModel = MainViewModel(viewModelUseCases)

        liveDataTestUtil = LiveDataTestUtil()

    }



    @Test
    fun getAirlines(){
        mainViewModel.getAirLinesList()
        val result = liveDataTestUtil.getValue(mainViewModel.getAirlines)
        Assert.assertNotNull(result)
    }
    @Test
    fun getLocalAirlines(){
        mainViewModel.getLocalAirLinesList()
        val result = liveDataTestUtil.getValue(mainViewModel.getLocalAirlines)
        Assert.assertNotNull(result)
    }

    @Test
    fun createAirline(){
        mainViewModel.createAirline(Airline("1","Test 1", "Test","Test","Test","Test","Test","1111"))
        val result = liveDataTestUtil.getValue(mainViewModel.createAirline)
        Assert.assertNotNull(result)
    }
    @Test
    fun createLocalAirline(){
        mainViewModel.createLocalAirline(listOf(Airline("2","Test 2", "Test","Test","Test","Test","Test","1111")))
        val result = liveDataTestUtil.getValue(mainViewModel.createLocalAirline)
        Assert.assertNotNull(result)
    }

    @After
    fun after(){
        mainViewModel.dispose()
    }

}


class ViewModelUseCasesMock: ViewModelUseCasesInterface{

    override val getAirlinesUseCase: GetAirlinesUseCase = GetAirlinesUseCase(AirlineRepositoryImplMock(),TestingIOSchedulers())

    override val createAirlineUseCase: CreateAirlineUseCase = CreateAirlineUseCase(AirlineRepositoryImplMock(),TestingIOSchedulers())
    override val getLocalAirlinesUseCase: GetLocalAirlinesUseCase = GetLocalAirlinesUseCase(AirlineLocalRepositoryImplMock(),TestingIOSchedulers())

    override val createLocalAirlineUseCase: CreateLocalAirlineUseCase = CreateLocalAirlineUseCase(AirlineLocalRepositoryImplMock(),TestingIOSchedulers())

    override val deleteLocalAirlinesUseCase: DeleteLocalAirlinesUseCase = DeleteLocalAirlinesUseCase(AirlineLocalRepositoryImplMock(),TestingIOSchedulers())


}





class AirlineRepositoryImplMock: AirlineRepository{
    override fun getAirlines(): Single<List<Airline>> {
        return Single.create{
            try {
                it.onSuccess(DataBaseTest.getAirlineTest())
            }
            catch (e:Exception){
                it.onError(Throwable(e.message))
            }
        }
    }

    override fun createAirline(airline: Airline): Single<Airline> {
        return Single.create{
            try {
                it.onSuccess(airline)
            }
            catch (e:Exception){
                it.onError(Throwable(e.message))
            }
        }
    }

}


class AirlineLocalRepositoryImplMock: AirlineLocalRepository {

    override fun insert(airline: Airline): Long {
        DataBaseTest.createAirline()
        return 0
    }

    override fun loadAll(): MutableList<Airline> {
       return DataBaseTest.getAirlineTest().toMutableList()
    }

    override fun deleteAll(): Int {
        val size= DataBaseTest.getAirlineTest().size
        DataBaseTest.deleteAirline()
        return size
    }

}