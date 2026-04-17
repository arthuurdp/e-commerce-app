package com.ecommerce.app.ui.customer.profile

import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentProfileBinding
import com.ecommerce.app.util.DialogUtils
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var profileOptionsAdapter: ProfileOptionsAdapter

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { uploadImage(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeProfile()
        observeProfilePicture()
        viewModel.loadProfile()
        setupRecyclerView()

        binding.ivAvatar.setOnClickListener {
            showImageOptions()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun setupRecyclerView() {
        profileOptionsAdapter = ProfileOptionsAdapter { option ->
            when (option.id) {
                1 -> findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
                2 -> findNavController().navigate(R.id.action_profileFragment_to_addressListFragment)
                3 -> findNavController().navigate(R.id.action_profileFragment_to_orderListFragment)
                4 -> findNavController().navigate(R.id.action_profileFragment_to_notificationsFragment)
                5 -> findNavController().navigate(R.id.action_profileFragment_to_securityFragment)
            }
        }

        binding.rvProfileOptions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = profileOptionsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        val options = listOf(
            ProfileOption(1, R.drawable.ic_pencil, "Editar Perfil"),
            ProfileOption(2, R.drawable.ic_location, "Meus Endereços"),
            ProfileOption(3, R.drawable.ic_order, "Meus Pedidos"),
            ProfileOption(4, R.drawable.ic_my_activity, "Minha Atividade"),
            ProfileOption(5, R.drawable.ic_lock, "Segurança")
        )
        profileOptionsAdapter.submitList(options)
    }

    private fun observeProfile() {
        viewModel.profileState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.layoutLoading.loadingOverlay.show()
                is NetworkResult.Success -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    val user = result.data
                    binding.tvFirstName.text = "${user.firstName} "
                    binding.tvLastName.text = "${user.lastName}"
                    binding.tvEmail.text = user.email

                    val placeholderRes = if (user.gender == "MALE") {
                        R.drawable.img_male
                    } else {
                        R.drawable.img_female
                    }

                    if (user.profilePictureUrl != null) {
                        Glide.with(this)
                            .load(user.profilePictureUrl)
                            .signature(ObjectKey(System.currentTimeMillis() / (1000 * 60)))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(placeholderRes)
                            .error(placeholderRes)
                            .into(binding.ivAvatar)
                    } else {
                        binding.ivAvatar.setImageResource(placeholderRes)
                    }
                }
                is NetworkResult.Error -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeProfilePicture() {
        viewModel.profilePictureState.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            when (result) {
                is NetworkResult.Loading -> binding.layoutLoading.loadingOverlay.show()
                is NetworkResult.Success -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    showToast("Profile picture updated")
                    viewModel.clearProfilePictureState()
                }
                is NetworkResult.Error -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    showToast(result.message)
                    viewModel.clearProfilePictureState()
                }
            }
        }
    }

    private fun showImageOptions() {
        DialogUtils.showOptionsDialog(
            context = requireContext(),
            item = Unit,
            editLabel = "Alterar foto",
            deleteLabel = "Remover foto",
            onEditClick = { pickImageLauncher.launch("image/*") },
            onDeleteClick = { viewModel.deleteProfilePicture() }
        )
    }

    private fun uploadImage(uri: Uri) {
        val contentResolver = requireContext().contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val byteArray = inputStream?.readBytes()
        inputStream?.close()

        if (byteArray != null) {
            val fileName = getFileName(uri) ?: "profile_picture.jpg"
            val requestFile = byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
            val body = MultipartBody.Part.createFormData("file", fileName, requestFile)
            viewModel.uploadProfilePicture(body)
        } else {
            showToast("Failed to read image")
        }
    }

    private fun getFileName(uri: Uri): String? {
        var name: String? = null
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    name = it.getString(nameIndex)
                }
            }
        }
        return name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}