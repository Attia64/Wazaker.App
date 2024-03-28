package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentMyAzkaarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAzkaarFragment : Fragment() {

    private lateinit var binding: FragmentMyAzkaarBinding
    private lateinit var viewModel: MyAzkaarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAzkaarBinding.inflate(inflater, container, false)

        val myAzkaarAdapter = MyAzkaarAdapter(listener = { chosenZekr ->
            findNavController().navigate(
                MyAzkaarFragmentDirections.actionMyAzkaarFragmentToCounterFragment(
                    chosenZekr
                )
            )
        })

        binding.rvAzkaar.apply {
            adapter = myAzkaarAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        viewModel = ViewModelProvider(this)[MyAzkaarViewModel::class.java]

        binding.azkaarViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.azkaarList.observe(viewLifecycleOwner, Observer {
            myAzkaarAdapter.submitList(it)
        })

        binding.ivAddButton.setOnClickListener {
            showEditTextDialog()
        }

        binding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_myAzkaarFragment_to_azkaarFragment)
        }


        return binding.root
    }

    private fun showEditTextDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.add_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_add)

        with(builder) {
            setTitle("إضافة ذكر")
            setPositiveButton("إضافة") { dialog, which ->
                val zekr = editText.text
                if (!zekr.isNullOrEmpty()) {
                    viewModel.addZekr(zekr.toString())
                    Toast.makeText(requireContext(), "تم إضافة الذكر", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "يرجى كتابة ذكر!", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("إلغاء") { dialog, which ->
                Log.d("Main", "Negative Button has Clikced")
            }
            setView(dialogLayout)
            show()
        }
    }

}