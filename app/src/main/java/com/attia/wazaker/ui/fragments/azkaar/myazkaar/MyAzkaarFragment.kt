package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentMyAzkaarBinding
import com.attia.wazaker.ui.SwipeToDeleteCallBack
import com.attia.wazaker.ui.fragments.counter.CounterFragment
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

        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = myAzkaarAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteItem(item)
                Toast.makeText(requireContext(), "تم حذف الذكر", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvAzkaar)


        viewModel = ViewModelProvider(this)[MyAzkaarViewModel::class.java]

        binding.azkaarViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.azkaarList.observe(viewLifecycleOwner, Observer {
            myAzkaarAdapter.submitList(it)
        })


        binding.ivAddButton.setOnClickListener {
            showDialog("إضافة ذكر") { value ->

                if (value.isEmpty()) {
                    Toast.makeText(requireContext(), "يرجى إضافة ذكر", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addZekr(value)
                    Toast.makeText(requireContext(), "تم إضافة الذكر", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_myAzkaarFragment_to_azkaarFragment)
        }


        return binding.root
    }

  private  fun showDialog(text: String, task: (String) -> (Unit)) {
        val layout = layoutInflater.inflate(R.layout.add_layout, null)
        val title = layout.findViewById<TextView>(R.id.tvTitle)
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        title.text = text
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

}