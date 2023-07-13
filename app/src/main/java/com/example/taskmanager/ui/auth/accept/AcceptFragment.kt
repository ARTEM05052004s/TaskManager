package com.example.taskmanager.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentAcceptBinding
import com.example.taskmanager.ui.auth.phone.PhoneFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class AcceptFragment : Fragment() {
    private lateinit var binding : FragmentAcceptBinding
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verId : String? = arguments?.getString(PhoneFragment.VER_KEY)

        binding.btnAccept.setOnClickListener{
            val credential = PhoneAuthProvider.getCredential(verId.toString(), binding.verifyView.textEntered.toString())
            signInWithPhoneAuthCredential(credential)
        }

        setPictureWithGlide()
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential).addOnSuccessListener {
                findNavController().navigate(R.id.action_to_mobile_navigation)
            }.addOnFailureListener{
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun setPictureWithGlide(){
        Glide.with(binding.ivCode).load("https://cdni.iconscout.com/illustration/premium/thumb/login-page-4468581-3783954.png").into(binding.ivCode)
    }
}
