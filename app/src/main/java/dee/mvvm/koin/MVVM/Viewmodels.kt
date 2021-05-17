package dee.mvvm.koin.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


open class Viewmodels constructor(
     val repository: Repository
): ViewModel()  {


     val AssestsResponse: MutableLiveData<Resource<Assests>> = MutableLiveData()
    val AssestsResponseviewmodel: LiveData<Resource<Assests>> get() = AssestsResponse

    //for Assests
      fun AssestsViewModel() = viewModelScope.launch {
        AssestsResponse.value = Resource.Loading
        AssestsResponse.value = repository.AssestsRepo()
    }



}