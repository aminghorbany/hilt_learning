package com.example.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataStore : DataStore<Preferences> by preferencesDataStore("appInfo") // the name of file
    // keys
    private val userNameKey = stringPreferencesKey("nameKey")
    private val userAgeKey = intPreferencesKey("ageKey")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            saveBtn.setOnClickListener {
                lifecycleScope.launch {
                    saveData(binding.nameEdt.text.toString() , 30)
                    showDataTxt.text = binding.nameEdt.text.toString()
                }
            }
            lifecycleScope.launch {
                getName().collect{
                    showDataTxt.text = it
                }
            }
        }
    }
    private suspend fun saveData(name : String , age : Int){
        dataStore.edit {
            it[userNameKey] = name
            it[userAgeKey] = 25
        }
    }

    private fun getName() = dataStore.data.map {
             it[userNameKey] ?: ""
    }
}