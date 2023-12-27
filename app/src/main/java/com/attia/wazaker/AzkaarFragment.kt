package com.attia.wazaker

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.database.WazakerDatabase
import com.attia.wazaker.databinding.FragmentAzkaarBinding

// TODO: Changed the fragment like the previous
class AzkaarFragment : Fragment() {

    private lateinit var binding: FragmentAzkaarBinding
    private lateinit var viewModel: AzkaarViewModel
    val azkaarList = mutableListOf(
        "سُبْحَانَ اللَّهِ",
        "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
        "سُبْحَانَ اللَّهِ العَظِيم وَبِحَمْدِهِ",
        "سُبْحَانَ اللَّهِ ، وَالحَمْدُ لِلَّهِ ،ولا إلهَ إلاَّ اللَّه  ، وَاللَّهُ أَكْبَرُ ",
        "الحَمْدُ لِلَّه",
        "الحَمْدُ حَمْدًا كَثِيرًا طَيِّبًا مُبَارَكًا فِيهِ",
        "أسْتَغْفِرُ الله",
        "أسْتَغْفِرُ اللهَ العَظِيم"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAzkaarBinding.inflate(inflater, container, false)

        val appliction = requireNotNull(this.activity).application
        val dataSource = WazakerDatabase.getInstance(appliction).AzkaarDatabaseDao
        val viewModelFactory = AzkaarViewModelFactory(dataSource, appliction)

        val azkaarViewModel = ViewModelProvider(
            this, viewModelFactory)[AzkaarViewModel::class.java]

        binding.azkaarViewModel = azkaarViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val azkarAdapter = AzkaarAdapter()
        azkarAdapter.submitList(azkaarList)

        binding.rvAzkaar.apply {
            adapter = azkarAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.azkaar_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.Add_menu -> showEditTextDialog()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showEditTextDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.add_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_add)

        with(builder){
            setTitle("إضافة ذكر")
            setPositiveButton("إضافة"){dialog, which ->
                if(!TextUtils.isEmpty(editText.text)) {
                    viewModel.passedZekr = editText.text.toString()
                    viewModel.addZekr()
                    Toast.makeText(requireContext(), "تم إضافة الذكر", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "يرجى كتابة ذكر!", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("إلغاء"){dialog, which ->
                Log.d("Main","Negative Button has Clikced")
            }
            setView(dialogLayout)
            show()
        }
    }

}