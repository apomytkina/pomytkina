package com.example.pomytkina

import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.example.pomytkina.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var gifCount: Int = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.previousButton.setOnClickListener {

        }

        binding.nextButton.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                binding.previousButton.isEnabled = true
                gifCount++
                binding.noteTextView.isVisible = true
                binding.imageView.isVisible = true
                binding.progressBar.isVisible = true

                val response = try {
                    RetrofitInstance.api.getRandomGif()
                } catch(e: IOException) {
                    Log.e(TAG, "IOException, you might not have internet connection")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                }
                if(response.isSuccessful && response.body() != null) {
                    val gif: Gif = response.body()!!
                    Glide.with(this@MainActivity)
                            .asGif()
                            .load(gif.gifURL)
                            .into(binding.imageView)
                } else{
                    Log.e(TAG, "Response not successful")
                }
                binding.progressBar.isVisible = false
            }
        }
    }
}