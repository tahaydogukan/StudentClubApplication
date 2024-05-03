package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.R
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
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubInfoAssignManagerBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel

class SksAdminClubInfoAssignManagerFragment : Fragment() {

    private lateinit var binding: FragmentSksAdminClubInfoAssignManagerBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    var nameList = ArrayList<String>()
    private val args: SksAdminClubInfoAssignManagerFragmentArgs by navArgs()

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentSksAdminClubInfoAssignManagerBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseViewModel.getClubManagers()

        navController = Navigation.findNavController(view)

        var selectedClubManager: String? = null
        var selectedClubManagerId: String? = null
        var clubManagerList2 = emptyList<ClubManager>()

        firebaseViewModel.clubManagers.observe(viewLifecycleOwner) { clubManagersList ->

            clubManagerList2 = clubManagersList
            Log.e("List", clubManagerList2.toString())

            for (i in clubManagersList) {

                nameList.add(i.name)

                }

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, nameList)
            binding.sksAdminClubsInfoAssignManagerAutoCompleteTextview.setAdapter(arrayAdapter)

            binding.sksAdminClubsInfoAssignManagerAutoCompleteTextview.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selectedText = parent.getItemAtPosition(position) // Seçilen nesneyi al
                    selectedClubManager = selectedText.toString() // Nesne adını al
                    // Seçilen metni kullanın
                }


        }


        binding.sksAdminClubInfoBtnAssignManagaer.setOnClickListener {


            val matchingIndex = clubManagerList2.indexOfFirst { it.name == selectedClubManager }

            if (matchingIndex != -1) {
                // Eşleşen nesne bulundu
                val matchingObject = clubManagerList2[matchingIndex]
                // Eşleşen nesneyle işlem yapın
                selectedClubManagerId = matchingObject.clubManagerId
                Log.e("ManagerIndex", matchingObject.clubManagerId.toString())
                firebaseViewModel.assignManager(
                    args.club.clubId,
                    selectedClubManagerId.toString(), requireContext()
                )

            } else {
                // Eşleşen nesne bulunamadı
                Log.e("ManagerIndex", "başarısız")

            }


        }

    }




}