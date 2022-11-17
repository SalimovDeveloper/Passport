package uz.salimovdeveloper.passport.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.salimovdeveloper.passport.R
import uz.salimovdeveloper.passport.adapters.RvAdapter
import uz.salimovdeveloper.passport.adapters.RvClick
import uz.salimovdeveloper.passport.databinding.FragmentHomeBinding
import uz.salimovdeveloper.passport.db.AppDatabase
import uz.salimovdeveloper.passport.models.User

class HomeFragment : Fragment(), RvClick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(requireContext())
        list = ArrayList()
        list.addAll(appDatabase.myPassportDao().getPassport())
        rvAdapter = RvAdapter(list, this)

        binding.myRecycl.adapter = rvAdapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        return binding.root
    }

    override fun itemClick(user: User, view: View) {
        findNavController().navigate(R.id.infoFragment, bundleOf("key" to user))
    }
}