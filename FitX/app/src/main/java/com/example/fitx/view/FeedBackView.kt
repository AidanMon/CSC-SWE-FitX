package com.example.fitx.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.FeedBackBinding
import com.example.fitx.repository.UserRepository

class FeedBackView: Fragment() {
    private var _binding: FeedBackBinding? = null
    private var userRepository = UserRepository()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FeedBackBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendButton.setOnClickListener{
            userRepository.retrieveFeedback(binding.messageEditText.text.toString())
            Toast.makeText(requireActivity(), "Thanks " + binding.nameEditText.text.toString() + " for your Feedback!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_Feedback_to_HomePage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
