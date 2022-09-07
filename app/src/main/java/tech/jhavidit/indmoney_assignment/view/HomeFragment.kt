package tech.jhavidit.indmoney_assignment.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tech.jhavidit.indmoney_assignment.databinding.FragmentHomeBinding
import tech.jhavidit.indmoney_assignment.repository.NetworkHelper
import tech.jhavidit.indmoney_assignment.utilities.LocalKeyStorage
import tech.jhavidit.indmoney_assignment.utilities.Status
import tech.jhavidit.indmoney_assignment.utilities.showToast
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: HomeFragmentViewModel by viewModels()

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = UserAdapter()

        viewModel.userResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                Status.SUCCESS -> {
                    adapter.setUserList(emptyList())
                    binding.recyclerView.adapter = adapter
                    binding.swipeRefresh.isRefreshing = false
                    it.data?.let { data ->
                        adapter.setUserList(data)
                    }
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    if (!networkHelper.isNetworkConnected()) {
                        showToast("Check your network connection",requireContext())
                    } else {
                        showToast(it.message.toString(),requireContext())
                    }
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchUsers()
        }

        return binding.root
    }


}