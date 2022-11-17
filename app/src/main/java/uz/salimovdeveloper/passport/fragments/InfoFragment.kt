package uz.salimovdeveloper.passport.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.salimovdeveloper.passport.R
import uz.salimovdeveloper.passport.databinding.FragmentInfoBinding
import uz.salimovdeveloper.passport.models.User

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)

        val user = arguments?.getSerializable("key") as User

        binding.imageInfo.setImageURI(Uri.parse(user.imagePath))
        binding.infoName.text = user.name
        binding.infoSurname.text = user.surname
        binding.infoSeria.text = user.seriya
        binding.infoBrithday.text = user.brith_day

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}