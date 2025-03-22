package com.attia.wazaker.ui.fragments.counter


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentCounterBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class CounterFragment : Fragment() {

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CounterViewModel
    private var isRunning = true
    private val args: CounterFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            showDialog(getString(R.string.add_step_value), hint = getString(R.string.add_step_hint)) { value ->
                if (value.isEmpty() || value.toInt() <= 0) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.step_must_be_more_than_one),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.step.value = value.toInt()
                }
            }

        }

        binding.btnGoal.setOnClickListener {
            showDialog(getString(R.string.add_goal), hint = getString(R.string.add_goal_hint)) { value ->
                if (value.isEmpty() || value.toInt() < 1) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.goal_must_be_more_than_one),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.tvGoal.text = getString(R.string.the_goal, value)
                }
            }
        }

        binding.tvAzkaar.setOnClickListener {
            findNavController().navigate(CounterFragmentDirections.actionCounterFragmentToMyAzkaarFragment())
        }

        binding.imSave.setOnClickListener {
            val zekrName = binding.tvAzkaar.text.toString()
            val numCount = binding.tvCounterShower.text.toString().toInt()
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            viewModel.addHistory(zekrName, time, numCount)
            Toast.makeText(requireContext(),
                getString(R.string.zekr_added_to_history), Toast.LENGTH_SHORT).show()
        }

    }


    @SuppressLint("InflateParams")
    private fun showDialog(text: String, hint: String, task: (String) -> (Unit)) {
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
        editTextValue.setHint(hint)
        posBtn.setOnClickListener {
            task(editTextValue.text.toString())

            dialog.dismiss()
        }
        negBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}