package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileEditRequestBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedDetailFragmentArgs

class ClubManagerProfileEditRequestFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerProfileEditRequestBinding
    private val args: SksAdminFormsApprovedDetailFragmentArgs by navArgs()
    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentClubManagerProfileEditRequestBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        var request = args.request

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.clubManagerProfileMyActivitiesEditPhoto)
        binding.clubAdminProfileMyActivitiesEditTitle.setText(args.request.title)
        binding.clubAdminProfileMyActivitiesEditDescription.setText(args.request.content)
        binding.clubAdminProfileMyActivitiesEditManager.setText(args.request.manager)
        binding.clubAdminProfileMyActivitiesEditLocation.setText(args.request.location)
        binding.clubAdminProfileMyActivitiesEditWebPlatform.setText(args.request.webPlatform)
        binding.clubAdminProfileMyActivitiesEditStartDate.setText(args.request.startDate)
        binding.clubAdminProfileMyActivitiesEditEndDate.setText(args.request.endDate)
        binding.clubAdminProfileMyActivitiesEditContacts.setText(args.request.contacts)


        binding.button2.setOnClickListener {
            requestViewModel.editPost(
                args.request.documentId,
                binding.clubAdminProfileMyActivitiesEditTitle.text.toString(),
                binding.clubAdminProfileMyActivitiesEditManager.text.toString(),
                binding.clubAdminProfileMyActivitiesEditDescription.text.toString(),
                null,
                binding.clubAdminProfileMyActivitiesEditStartDate.text.toString(),
                binding.clubAdminProfileMyActivitiesEditEndDate.text.toString(),
                binding.clubAdminProfileMyActivitiesEditLocation.text.toString(),
                binding.clubAdminProfileMyActivitiesEditWebPlatform.text.toString(),
                binding.clubAdminProfileMyActivitiesEditContacts.text.toString(),
                requireContext()
            )

            navController.navigate(R.id.clubManagerProfileFragment)
            navController.popBackStack()


        }


    }


}