package tech.jhavidit.indmoney_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_item_list.*
import tech.jhavidit.indmoney_assignment.databinding.FragmentUserDetailsBinding

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val args : UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.name.text = args.userDetails.name
        binding.contact.text = args.userDetails.phoneNumber
        binding.description.text = args.userDetails.description
        Glide.with(requireContext())
            .load(args.userDetails.imageUrl)
            .into(binding.userImage)
        binding.title.text = args.userDetails.title
        return binding.root
    }

}