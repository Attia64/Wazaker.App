package com.attia.wazaker.ui.fragments.counter


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
    private lateinit var viewModel: CounterViewModel
    private var isRunning = false
    private val args: CounterFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]


        binding.counterViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            btnPlay.setOnClickListener {
                if (isRunning) {
                    btnPlay.setImageResource(R.drawable.ic_pause)
                    chCounter.start()
                    viewModel.flag = true
                    viewModel.automationTasbih()
                    isRunning = false
                } else {
                    btnPlay.setImageResource(R.drawable.ic_play_button)
                    chCounter.stop()
                    viewModel.flag = false
                    isRunning = true
                }
            }
        }

        binding.btnStep.setOnClickListener {
            showDialog("إضافة مقدار العدة") { value ->
                if (value.isEmpty() || value.toInt() <= 0) {
                    Toast.makeText(
                        requireContext(),
                        "لايمكن أن تكون العدة أقل من 1",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.step.value = value.toInt()
                }
            }

        }
        binding.btnGoal.setOnClickListener {
            showDialog("إضافة هدف") { value ->
                if (value.isEmpty() || value.toInt() < 1) {
                    Toast.makeText(
                        requireContext(),
                        "لايمكن أن يكون الهدف أقل من 1",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.tvGoal.text = "الهدف : $value"
                }
            }
        }

        binding.tvAzkaar.setOnClickListener {
            findNavController().navigate(CounterFragmentDirections.actionCounterFragmentToMyAzkaarFragment())
        }

        binding.imSave.setOnClickListener {
            val zekrName = binding.tvAzkaar.text.toString()
            val numcount = binding.tvCounterShower.text.toString().toInt()
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            viewModel.addHistory(zekrName, time, numcount)
            Toast.makeText(requireContext(), "تم الإضافة إلي سجل الذكر", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }


    fun showDialog(text: String, task: (String) -> (Unit)) {
        val layout = layoutInflater.inflate(R.layout.add_layout, null)
        val title = layout.findViewById<TextView>(R.id.tvTitle)
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        title.text = text
        dialog.setContentView(layout)
        val posBtn = layout.findViewById<Button>(R.id.btnOK)
        val negBtn = layout.findViewById<View>(R.id.btnCancel)
        val editTextValue = layout.findViewById<EditText>(R.id.etDialog)
        editTextValue.inputType = InputType.TYPE_CLASS_NUMBER
        posBtn.setOnClickListener {
            task(editTextValue.text.toString())
            dialog.dismiss()
        }
        negBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}