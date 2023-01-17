package com.todokanai.roompractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.todokanai.roompractice.repository.DataStoreRepository
import com.todokanai.roompractice.repository.UserRepository
import com.todokanai.roompractice.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val dataStoreRepository = DataStoreRepository()


    suspend fun test() = withContext(Dispatchers.Default){ userRepository.getUserByIndex(1) }


    val firstVal = userRepository.getUserByIndex(1).asLiveData()       // Dao에서 @Query getFirst()에 해당

    val firstFlow = userRepository.getUserByIndex(1)


    val allUser : LiveData<List<User>> = userRepository.getUsers().asLiveData()



    fun insert(user: User) = userRepository.insert(user)            //

    fun deleteAll() = userRepository.deleteAll()

    fun dataStoreSave(value:String) = dataStoreRepository.save(value)


    // return 값이 있을경우 viewModel 단계에서 val 취급하기
}