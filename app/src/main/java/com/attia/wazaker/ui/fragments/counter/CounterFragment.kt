package com.attia.wazaker.ui.fragments.counter


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputType
import android.util.Log
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
    private var isRunning = true
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

        binding.tvAzkaar.text = args.chossenzekr

        binding.apply {
            btnPlay.setOnClickListener {
                if (isRunning) {
                    btnPlay.setImageResource(R.drawable.ic_pause)
                    viewModel.automationTasbih()
                    isRunning = false
                } else {
                    btnPlay.setImageResource(R.drawable.ic_play_button)
                    viewModel.cancelAutomationTasbih()
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

        var target = 100
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
                    target = value.toInt()
                }
            }
        }

        viewModel.counter.observe(viewLifecycleOwner) { value ->

            if (value == target) {
                targetEvent()
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


    @SuppressLint("InflateParams")
    private fun showDialog(text: String, task: (String) -> (Unit)) {
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


    private fun targetEvent() {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    1000,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(1000)
        }
    }

}