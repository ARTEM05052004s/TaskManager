package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskmanager.data.Car
import com.example.taskmanager.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private val db = Firebase.firestore
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener{
            setData()
        }
    }

    private fun setData() {
        val data = Car(
            label = binding.etLabel.text.toString(),
            model = binding.etModel.text.toString()
        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .document()
            .set(data)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Successfully saved!", Toast.LENGTH_SHORT).show()
                binding.etLabel.text.clear()
                binding.etModel.text.clear()
            }.addOnFailureListener{
                Log.e("ololo", "setData: $it")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}