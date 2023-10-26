package com.example.flo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.flo.databinding.ActivityNaviBinding


private const val TAG_HOME = "home_fragment"
private const val TAG_LOOK = "look_fragment"
private const val TAG_SEARCH = "search_fragment"
private const val TAG_LOCKER = "locker_fragment"

class NaviActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val song = Song(binding.mainMiniplayerTitleTv.text.toString(), binding.mainMiniplayerSingerTv.text.toString(), 0, 60, false)
        Log.d("Song", song.title + song.singer)

        binding.mainPlayerCl.setOnClickListener{
            //startActivity(Intent(this, SongActivity::class.java))
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playtime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            startActivity(intent)
        }



        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.lookFragment -> setFragment(TAG_LOOK, LookFragment())
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.searchFragment-> setFragment(TAG_SEARCH, SearchFragment())
                R.id.lockerFragment-> setFragment(TAG_LOCKER, LockerFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val look = manager.findFragmentByTag(TAG_LOOK)
        val home = manager.findFragmentByTag(TAG_HOME)
        val search = manager.findFragmentByTag(TAG_SEARCH)
        val locker = manager.findFragmentByTag(TAG_LOCKER)

        if (look != null){
            fragTransaction.hide(look)
        }

        if (home != null){
            fragTransaction.hide(home)
        }

        if (search != null) {
            fragTransaction.hide(search)
        }

        if (locker != null) {
            fragTransaction.hide(locker)
        }

        if (tag == TAG_LOOK) {
            if (look!=null){
                fragTransaction.show(look)
            }
        }
        else if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        }

        else if (tag == TAG_SEARCH){
            if (search != null){
                fragTransaction.show(search)
            }
        }

        else if (tag == TAG_LOCKER){
            if (locker != null){
                fragTransaction.show(locker)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}