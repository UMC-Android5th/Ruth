package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.song1.setOnClickListener{
            (context as NaviActivity).supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout,AlbumFragment()).commitAllowingStateLoss()
        }

        val bannerVPAdapter = BannerVPAdapter(this)
        bannerVPAdapter.addFragment(Bannerfragment(R.drawable.img_home_viewpager_exp))
        bannerVPAdapter.addFragment(Bannerfragment(R.drawable.img_home_viewpager_exp2))
        binding.banner.adapter = bannerVPAdapter
        binding.banner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }


}