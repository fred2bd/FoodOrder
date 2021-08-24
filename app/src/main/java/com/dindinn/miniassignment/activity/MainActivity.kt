package com.dindinn.miniassignment.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dindinn.miniassignment.FragmentToActivityCommunicationViewModel
import com.dindinn.miniassignment.R
import com.dindinn.miniassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private val communicationViewModel: FragmentToActivityCommunicationViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)







        mp = MediaPlayer.create(this, R.raw.alarm)
        mp.isLooping = true

        communicationViewModel.playAlarmLiveData.observe(this, Observer {

            if (it) {
                if (!mp.isPlaying)
                    mp.start()

            } else {
                if (mp.isPlaying)
                    mp.stop()
            }

        })


        communicationViewModel.showProgressbarLiveData.observe(this, Observer {
            if (it) {
                binding.progressbar.visibility = View.VISIBLE
            }else{
                binding.progressbar.visibility = View.GONE

            }
        })


    }
}