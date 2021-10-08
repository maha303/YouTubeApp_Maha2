package com.example.youtubeapp_maha2

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

private lateinit var rvAdapter :RecyclerView
 private  lateinit var youTubePlayerView : YouTubePlayerView
 lateinit var player :YouTubePlayer

private var Video = 0
private var time = 0f

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting
        if (activeNetwork?.isConnectedOrConnecting == false) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Internet Connection Not Found")
                .show()
        }

        val items = arrayOf(arrayOf(" Positive Energy  ","BWMcR35D-cE"),
            arrayOf(" Anxiety","O-6f5wQXSu8"),
            arrayOf(" Relaxation ","koRbYQyPU0U"),
            arrayOf(" Nature Sounds","HCx_L2QwxX4"),
            )
        youTubePlayerView = findViewById(R.id.youT)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player=youTubePlayer
                player.loadVideo(items[Video][0],time)

            }
        })
        rvAdapter=findViewById(R.id.rvAd)
        rvAdapter.adapter=RVAdapter(items)
        rvAdapter.layoutManager=LinearLayoutManager(this)
        rvAdapter.setHasFixedSize(true)


    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            youTubePlayerView.enterFullScreen()
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("Video",Video)
        outState.putFloat("times", time)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Video=savedInstanceState.getInt("Video",0)
        time=savedInstanceState.getFloat("times",0f)
    }
}
