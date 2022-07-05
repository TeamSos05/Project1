package team.tiktok.tiktokapp.fragments.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import team.tiktok.tiktokapp.R
import team.tiktok.tiktokapp.adapter.detail.DetailViewpagerAdapter
import team.tiktok.tiktokapp.databinding.FragmentProfileBinding


class ProfileFM : Fragment() {
    private val IMAGE_REQ = 1
    private var imagePath: Uri? = null
    lateinit var binding: FragmentProfileBinding
    lateinit var adapter:DetailViewpagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        clickImage()
        initViewPager()
        clickButton()
        return binding.root
    }

    private fun clickButton() {
        binding.btnEditProfile.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_profileFM_to_editProfileFM)
            }
        }
        binding.ivList.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_profileFM_to_profileBottomSheetFM)
            }
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(
            binding.tab,
            binding.vpDetail
        ) { tab, position ->
            when (position){
                0->{
                    tab.setIcon(R.drawable.list)
                }
                1->{
                    tab.setIcon(R.drawable.resource_private)

                }
                2->{
                    tab.setIcon(R.drawable.heart)

                }
                3->{
                    tab.setIcon(R.drawable.favourite)

                }
            }
        }.attach()
    }

    private fun initViewPager() {
        adapter = DetailViewpagerAdapter(this)
        binding.vpDetail.adapter = adapter
        initTabLayout()
    }

    private fun clickImage() {
        binding.civAvatar.apply {
            setOnClickListener {
                requestPermission()
                Toast.makeText(requireActivity(), "OK", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            selectImage()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), IMAGE_REQ
            )
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*" // if you want to you can use pdf/gif/video
        intent.action = Intent.ACTION_GET_CONTENT
        someActivityResultLauncher.launch(intent)
    }




    private var someActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val data = result.data
            imagePath = data!!.data
            val handle = Handler(Looper.myLooper()!!)
            handle.postDelayed({
                Picasso.get().load(imagePath).into(binding.civAvatar)
            },800)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding == null
    }
}