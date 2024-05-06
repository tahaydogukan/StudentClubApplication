package com.tahayasindogukan.studentclubapplication.ui.home.student

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
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentClubInfoBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.SksAdminClubInfoRecyclerViewAdapter

class StudentClubInfoFragment : Fragment(),
    SksAdminClubInfoRecyclerViewAdapter.SksAdminClubInfoClickListener {

    private lateinit var binding: FragmentStudentClubInfoBinding
    private val args: StudentClubInfoFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private lateinit var adapter: SksAdminClubInfoRecyclerViewAdapter

    private val requestViewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentClubInfoBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //Recycler view le ilgili kodlar

        binding.sksAdminClubInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        requestViewModel.clubRequests.observe(viewLifecycleOwner) {

            val recyclerView = binding.sksAdminClubInfoRecyclerView
            adapter = SksAdminClubInfoRecyclerViewAdapter(it, this, requireContext())
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

        Glide.with(requireContext()).load(args.club.clubPhoto)
            .into(binding.sksAdminClubInfoIwClubPhoto)
        binding.sksAdminClubInfoTwClubName.text = args.club.clubName
        binding.sksAdminClubInfoTwMissionStatement.text = args.club.clubDescription

        Log.e("Club Delete", args.club.clubId)



    }

    override fun onClick(request: Request) {
        val action = StudentClubInfoFragmentDirections
            .actionStudentClubInfoFragmentToStudentClubInfoDetailFragment(request)
        findNavController().navigate(action)
    }

}