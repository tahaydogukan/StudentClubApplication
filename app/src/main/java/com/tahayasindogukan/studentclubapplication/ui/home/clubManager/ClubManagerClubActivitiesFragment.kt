package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

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
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerClubActivitiesBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.SksAdminClubInfoRecyclerViewAdapter

class ClubManagerClubActivitiesFragment : Fragment(),
    SksAdminClubInfoRecyclerViewAdapter.SksAdminClubInfoClickListener {

    private lateinit var binding: FragmentClubManagerClubActivitiesBinding
    private val args: ClubManagerClubActivitiesFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private lateinit var adapter: SksAdminClubInfoRecyclerViewAdapter

    private val requestViewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerClubActivitiesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //Recycler view le ilgili kodlar

        binding.clubManagerClubActivitiesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        requestViewModel.clubRequests.observe(viewLifecycleOwner) {

            val recyclerView = binding.clubManagerClubActivitiesRecyclerView
            adapter = SksAdminClubInfoRecyclerViewAdapter(it, this, requireContext())
            recyclerView.adapter = adapter

        }

        requestViewModel.getClubRequests(args.club.clubName)

        //Recycler view le ilgili kodlar

        var clubManagerId = args.club.clubManagerId


        FirebaseFirestore.getInstance().collection("clubManager").document(clubManagerId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.exists()) {
                    var clubManager = querySnapshot.toObject(ClubManager::class.java)

                    binding.clubManagerClubActivitiesClubManager.text = clubManager!!.name

                    Log.e("clubManager", " İşlem Başaraılı")
                } else {
                    Log.e("clubManager", "İşlem başarısız")
                }
            }

        Glide.with(requireContext()).load(args.club.clubPhoto)
            .into(binding.clubManagerClubActivitiesClubPhoto)
        binding.clubManagerClubActivitiesClubName.text = args.club.clubName
        binding.clubManagerClubActivitiesMissionStatement.text = args.club.clubDescription

        Log.e("Club Delete", args.club.clubId)


    }

    override fun onClick(request: Request) {
        val action = ClubManagerClubActivitiesFragmentDirections
            .actionClubManagerClubActivitiesFragmentToClubManagerClubActivtiesDetailFragment(request)
        findNavController().navigate(action)
    }

}