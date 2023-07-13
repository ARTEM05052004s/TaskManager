package com.example.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImage()
        initName()
    }

    private fun initImage() {
        Glide.with(binding.ivProfileImage).load(pref.getImage()).into(binding.ivProfileImage)
        binding.ivProfileImage.setOnClickListener {
            chooseImageContract.launch("image/*")
        }
    }

    private val chooseImageContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
            if (image != null) {
                pref.saveImage(image.toString())
                Glide.with(requireContext()).load(image)
                    .into(binding.ivProfileImage)
            }
        }

    private fun initName() {
        binding.etName.setText(pref.getName())
        binding.btnSaveUserName.setOnClickListener {
            pref.saveUserName(binding.etName.text.toString())
        }
    }

}