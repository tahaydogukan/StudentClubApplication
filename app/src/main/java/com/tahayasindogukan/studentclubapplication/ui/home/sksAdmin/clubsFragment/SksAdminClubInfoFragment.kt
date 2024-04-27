package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubInfoBinding


class SksAdminClubInfoFragment : Fragment(),SksAdminClubInfoRecyclerViewAdapter.SksAdminClubInfoClickListener {

    private lateinit var binding: FragmentSksAdminClubInfoBinding
    private val args : SksAdminClubInfoFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private lateinit var adapter: SksAdminClubInfoRecyclerViewAdapter

    private val requestViewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminClubInfoBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //Recycler view le ilgili kodlar

        binding.sksAdminClubInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext())






        requestViewModel.clubRequests.observe(viewLifecycleOwner){

            val recyclerView = binding.sksAdminClubInfoRecyclerView
            adapter = SksAdminClubInfoRecyclerViewAdapter(it,this,requireContext())
            recyclerView.adapter = adapter

        }

        requestViewModel.getClubRequests(args.club.clubName)

        //Recycler view le ilgili kodlar

        var clubManagerId = args.club.clubManagerId

        val clubForAssigningManager = args.club


        FirebaseFirestore.getInstance().collection("clubManager").document(clubManagerId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.exists()) {
                    var clubManager = querySnapshot.toObject(ClubManager::class.java)

                    binding.sksAdminClubInfoTwClubManager.text = clubManager!!.name

                    Log.e("clubManager", " İşlem Başaraılı")
                } else {
                    Log.e("clubManager", "İşlem başarısız")
                }
            }

        Glide.with(requireContext()).load(args.club.clubPhoto).into(binding.sksAdminClubInfoIwClubPhoto)
        binding.sksAdminClubInfoTwClubName.text = args.club.clubName
        binding.sksAdminClubInfoTwMissionStatement.text = args.club.clubDescription

        Log.e("Club Delete", args.club.clubId)


        binding.sksAdminClubInfoBtnDeleteClub.setOnClickListener {
            Snackbar.make(it,"Are you sure to delete?", Snackbar.LENGTH_LONG)
                .setAction("YES"){

                    val ref =FirebaseFirestore.getInstance()
                    val collectionRef = ref.collection("club").document(args.club.clubId)
                    collectionRef.delete()
                        .addOnSuccessListener {
                            Log.d("Club Delete", "Belge silindi!")
                            navController.navigate(R.id.sksAdminClubsFragment)
                            navController.popBackStack()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Club Delete", "Belge silme hatası: ${e.message}")
                            Snackbar.make(it,"Not Deleted",Snackbar.LENGTH_SHORT).show()
                        }


                }
                .show()
        }


        binding.sksAdminClubInfoBtnReassignClubManager.setOnClickListener {
            val action =SksAdminClubInfoFragmentDirections
                                    .actionSksAdminClubInfoFragmentToSksAdminClubInfoAssignManagerFragment(clubForAssigningManager)

            findNavController().navigate(action)
        }



    }

    override fun onClick(request: Request) {
        val action = SksAdminClubInfoFragmentDirections
            .actionSksAdminClubInfoFragmentToSksAdminClubInfoDetailFragment(request)
        findNavController().navigate(action)
    }

}