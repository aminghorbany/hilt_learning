package com.example.koin.scopes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.R
import com.example.koin.databinding.ActivityScopesBinding
import org.koin.androidx.scope.activityScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class ScopesActivity : AppCompatActivity() , KoinScopeComponent {


    private lateinit var binding: ActivityScopesBinding

    // implement KoinScopeComponent and init  scope and  personInfo and use them
    override val scope: Scope by activityScope()
    private val personInfo : PersonInfo by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScopesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            txtInfo.text = personInfo.showInfo()
        }
    }


}