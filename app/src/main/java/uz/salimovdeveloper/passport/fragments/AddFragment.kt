package uz.salimovdeveloper.passport.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import uz.salimovdeveloper.passport.R
import uz.salimovdeveloper.passport.databinding.FragmentAddBinding
import uz.salimovdeveloper.passport.db.AppDatabase
import uz.salimovdeveloper.passport.models.User
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var uriPath: String
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        uriPath = ""

        appDatabase = AppDatabase.getInstance(requireContext())

        val seria = ('A'..'A').random().toString() + ('A'..'C').random()
            .toString() + (1000000..9999999).random().toString()

        binding.imageAdd.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.brithDay.setOnClickListener {
            val date = Date()
            val datePickerDialog = DatePickerDialog(
                requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        Toast.makeText(
                            requireContext(),
                            "$dayOfMonth.${month+1}.$year",
                            Toast.LENGTH_SHORT
                        ).show()
                        val brith = "$dayOfMonth.${month+1}.$year"
                        binding.brithDay.text = brith
                    }
                },
                date.year,
                date.month,
                date.day
            )
            datePickerDialog.show()
        }


        binding.btnSave.setOnClickListener {
            var name = binding.name.text.toString()
            var surname = binding.surname.text.toString()
            var brithday = binding.brithDay.text.toString()

            if (name != "" && surname != "" && brithday != "") {
                val user: User = User(
                    name = binding.name.text.toString(),
                    surname = binding.surname.text.toString(),
                    brith_day = binding.brithDay.text.toString(),
                    seriya = seria.toString(),
                    imagePath = uriPath,
                    id = 0
                )
                appDatabase.myPassportDao().addPassport(user)
                findNavController().popBackStack()
                Toast.makeText(requireContext(), "Saqlandi!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Barcha maydonlarni toldiring!!!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }

        return binding.root
    }

    val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it ?: return@registerForActivityResult
        binding.imageAdd.setImageURI(it)
        val inputStream = requireActivity().contentResolver.openInputStream(it)
        val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val file = File(requireActivity().filesDir, "$title.jpg")
        val fileOutputStream = FileOutputStream(file)
        inputStream?.copyTo(fileOutputStream)
        inputStream?.close()
        fileOutputStream.close()
        uriPath = file.absolutePath
    }
}