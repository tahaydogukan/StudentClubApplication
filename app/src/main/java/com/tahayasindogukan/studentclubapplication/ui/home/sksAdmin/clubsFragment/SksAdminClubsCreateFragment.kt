package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubsCreateBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class SksAdminClubsCreateFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminClubsCreateBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    var nameList = ArrayList<String>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSksAdminClubsCreateBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        var selectedClubManager:String? = null
        var selectedClubManagerId:String?=null
        var clubManagerList2 = ArrayList<ClubManager>()
        firebaseViewModel.getClubManagers()
        firebaseViewModel.clubManagers.observe(viewLifecycleOwner){clubManagersList->

            clubManagerList2 = clubManagersList as ArrayList<ClubManager>

        for (i in clubManagersList){
            nameList.add(i.name)
        }
            val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,nameList)
            binding.sksAdminClubsCreateAutoCompleteTextView.setAdapter(arrayAdapter)

            binding.sksAdminClubsCreateAutoCompleteTextView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selectedText = parent.getItemAtPosition(position) // Seçilen nesneyi al
                    selectedClubManager = selectedText.toString() // Nesne adını al
                    // Seçilen metni kullanın
                }



        }




        val categoryList = ArrayList<String>()
        categoryList.add("health")
        categoryList.add("sport")
        categoryList.add("academic")
        categoryList.add("art")
        categoryList.add("technology")
        categoryList.add("social activism")

        val arrayAdapterCategory = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,categoryList)
        binding.sksAdminClubsCreateCategoryAutoCompleteTextView.setAdapter(arrayAdapterCategory)

        var selectedCategory:String? = null



        binding.sksAdminClubsCreateCategoryAutoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedText = parent.getItemAtPosition(position) // Seçilen nesneyi al
                selectedCategory = selectedText.toString() // Nesne adını al
                // Seçilen metni kullanın
            }

        binding.sksAdminClubsCreateBtnCreateClub.setOnClickListener {


            val matchingIndex = clubManagerList2.indexOfFirst { it.name == selectedClubManager }

            if (matchingIndex != -1) {
                // Eşleşen nesne bulundu
                val matchingObject = clubManagerList2[matchingIndex]
                // Eşleşen nesneyle işlem yapın
                Log.e("ManagerIndex",matchingIndex.toString())
                selectedClubManagerId = matchingObject.clubManagerId
            } else {
                // Eşleşen nesne bulunamadı
            }



            firebaseViewModel.createClub(
                "",
                selectedClubManagerId,
                binding.sksAdminClubsCreateEtClubName.text.toString(),
                "",
                binding.sksAdminClubsCreateEtClubDescription.text.toString(),
                "",
                "",
                "",
                selectedCategory!!,
                "1",
                requireContext()
            )
            Thread.sleep(2000)
            navController.navigate(R.id.sksAdminClubsFragment)
            navController.popBackStack()

        }



    }
}