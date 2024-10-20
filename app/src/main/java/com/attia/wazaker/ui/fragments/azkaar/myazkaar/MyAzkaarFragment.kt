package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentMyAzkaarBinding
import com.attia.wazaker.ui.SwipeToDeleteCallBack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAzkaarFragment : Fragment() {

    private var _binding: FragmentMyAzkaarBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyAzkaarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyAzkaarBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MyAzkaarViewModel::class.java]



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myAzkaarAdapter = MyAzkaarAdapter(listener = { chosenZekr ->
            Log.i("chosen Azkaar", chosenZekr)
            val action = MyAzkaarFragmentDirections.actionMyAzkaarFragmentToCounterFragment()
            action.chossenzekr = chosenZekr
            findNavController().navigate(action)
        })

        binding.rvAzkaar.apply {
            adapter = myAzkaarAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = myAzkaarAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteItem(item)
                Toast.makeText(requireContext(), "تم حذف الذكر", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvAzkaar)

        binding.azkaarViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.azkaarList.observe(viewLifecycleOwner) {
            myAzkaarAdapter.submitList(it)
        }


        binding.ivAddButton.setOnClickListener {
            showDialog { value ->

                if (value.isEmpty()) {
                    Toast.makeText(requireContext(),
                        getString(R.string.please_add_zekr), Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addZekr(value)
                    Toast.makeText(requireContext(),
                        getString(R.string.zekr_add_successfully), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_myAzkaarFragment_to_azkaarFragment)
        }

    }

    @SuppressLint("InflateParams")
    private fun showDialog(task: (String) -> (Unit)) {
        val layout = layoutInflater.inflate(R.layout.add_layout, null)
        val title = layout.findViewById<TextView>(R.id.tvTitle)
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        title.text = getString(R.string.add_zekr)
        dialog.setContentView(layout)
        val posBtn = layout.findViewById<Button>(R.id.btnOK)
        val negBtn = layout.findViewById<View>(R.id.btnCancel)
        val editTextValue = layout.findViewById<EditText>(R.id.etDialog)
        editTextValue.inputType = InputType.TYPE_CLASS_TEXT
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