package com.attia.wazaker.ui.fragments.counter

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.attia.wazaker.databinding.FragmentCounterBinding
import com.attia.wazaker.R
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding
    val args: CounterFragmentArgs by navArgs()
    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]


        binding.counterViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        binding.btnStep.setOnClickListener {
            showStepDialog()
        }

        binding.imSave.setOnClickListener {
            val zekrName = binding.tvAzkaar.text.toString()
            val numcount = binding.tvCounterShower.text.toString().toInt()
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            viewModel.addHistory(zekrName, time, numcount)
        }


        return binding.root
    }


    private fun showStepDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.step_dialog_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_step)

        with(builder) {
            setTitle("إضافة مقدار العدة")
            setPositiveButton("حسناً") { _, _ ->
                val stepVal = editText.text.toString().toInt()
                if (stepVal > 0) {
                    viewModel.step.value = editText.text.toString().toInt()
                } else
                    Toast.makeText(requireContext(), "يرجى إدخال قيمة صحيحة", Toast.LENGTH_SHORT)
                        .show()
            }
            setNegativeButton("إلغاء") { _, _ ->
                Log.d("Main", "Negative Button has Clicked")
            }
            setView(dialogLayout)
            show()
        }
    }
}