package com.ecommerce.app.ui.customer.search

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.databinding.FragmentSearchBinding
import com.ecommerce.app.ui.customer.products.ProductSearchAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.show
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

private val CATEGORY_COLORS = listOf(
    0xFF1DB954.toInt(), 0xFFFF6B35.toInt(), 0xFFE91E8C.toInt(),
    0xFF3D5AFE.toInt(), 0xFFFF6F00.toInt(), 0xFF00BCD4.toInt(),
    0xFF8E24AA.toInt(), 0xFFD32F2F.toInt(), 0xFF2196F3.toInt(),
    0xFF388E3C.toInt(),
)

private val CATEGORY_EMOJIS = mapOf(
    "lanche" to "🍔", "burger" to "🍔", "pizza" to "🍕",
    "sushi" to "🍣", "japonesa" to "🍣", "bebida" to "🥤", "drink" to "🥤",
    "doce" to "🍰", "sobremesa" to "🍰", "bolo" to "🍰",
    "mercado" to "🛒", "hortifruti" to "🥦", "farmácia" to "💊", "farmacia" to "💊",
    "açaí" to "🫐", "acai" to "🫐", "fit" to "💪", "saudável" to "🥗", "saudavel" to "🥗",
    "mexicana" to "🌮", "tacos" to "🌮", "italiana" to "🍝", "massa" to "🍝",
    "frango" to "🍗", "chicken" to "🍗", "peixe" to "🐟", "frutos" to "🦞",
    "vegano" to "🌱", "vegetariano" to "🌿", "gourmet" to "⭐",
    "chinesa" to "🥡", "árabe" to "🥙", "arabe" to "🥙",
)

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    private lateinit var resultsAdapter: ProductSearchAdapter
    private var loadedCategories: List<CategoryResponse> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupResultsList()
        setupSearchBar()
        observeCategories()
        observeSearch()
        viewModel.loadCategories()

        val categoryId: Long =
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.remove<Long>("categoryId")
                ?: arguments?.getLong("categoryId", -1L)
                ?: -1L

        if (categoryId != -1L) {
            showChipRow()
            binding.btnCancel.show()
            viewModel.search("", categoryId, force = true)
        }
    }

    private fun setupResultsList() {
        resultsAdapter = ProductSearchAdapter { product ->
            findNavController().navigate(
                R.id.action_searchFragment_to_productDetailFragment,
                bundleOf("productId" to product.id)
            )
        }
        binding.rvSearchResults.apply {
            val layoutManager = LinearLayoutManager(requireContext())
            this.layoutManager = layoutManager
            adapter = resultsAdapter

            val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
            ContextCompat.getDrawable(requireContext(), R.drawable.divider_style)?.let {
                divider.setDrawable(it)
            } ?: run {
                val insetDivider = DividerItemDecoration(requireContext(), layoutManager.orientation)
                this.addItemDecoration(insetDivider)
            }
            this.addItemDecoration(divider)
        }
    }

    private fun setupSearchBar() {
        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showChipRow()
                binding.btnCancel.show()
            }
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s?.toString() ?: "")
            }
        })

        binding.btnCancel.setOnClickListener {
            binding.etSearch.clearFocus()
            binding.etSearch.setText("")
            hideKeyboard()
            viewModel.clearSearch()
            hideChipRow()
            showEmptyState()
        }
    }

    private fun showChipRow() {
        binding.chipScrollView.apply {
            if (visibility != View.VISIBLE) {
                alpha = 0f
                translationY = -8f.dpToPx()
                show()
                animate().alpha(1f).translationY(0f).setDuration(220).start()
            }
        }
    }

    private fun hideChipRow() {
        binding.chipScrollView.animate()
            .alpha(0f)
            .translationY(-8f.dpToPx())
            .setDuration(180)
            .withEndAction { binding.chipScrollView.hide() }
            .start()
    }

    private fun observeCategories() {
        viewModel.categoriesState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                loadedCategories = result.data.content
                buildCategoryChips(loadedCategories)
                buildCategoryGrid(loadedCategories)
            }
        }
    }

    private fun observeSearch() {
        viewModel.searchState.observe(viewLifecycleOwner) { result ->
            when (result) {
                null -> showEmptyState()

                is NetworkResult.Loading -> {
                    hideAllStates()
                    binding.progressBar.show()
                }

                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val items = result.data.content
                    if (items.isEmpty()) {
                        showNoResults()
                    } else {
                        resultsAdapter.submitList(items)
                        val count = result.data.totalElements
                        binding.tvResultCount.text =
                            "$count ${if (count == 1L) "produto encontrado" else "produtos encontrados"}"
                        showResults()
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showNoResults()
                }
            }
        }
    }

    private fun buildCategoryChips(categories: List<CategoryResponse>) {
        val group = binding.chipGroup
        group.removeAllViews()

        val allChip = createChip(
            label = "Todos",
            emoji = "🍽️",
            isSelected = viewModel.selectedCategoryId == null,
            accentColor = requireContext().getColor(R.color.primary)
        )
        allChip.setOnClickListener {
            selectChip(group, allChip)
            viewModel.search(binding.etSearch.text?.toString() ?: "", categoryId = null)
        }
        group.addView(allChip)

        categories.forEachIndexed { index, category ->
            val chip = createChip(
                label  = category.name,
                emoji  = resolveEmoji(category.name),
                isSelected  = viewModel.selectedCategoryId == category.id,
                accentColor = CATEGORY_COLORS[index % CATEGORY_COLORS.size]
            )
            chip.setOnClickListener {
                selectChip(group, chip)
                viewModel.search(
                    binding.etSearch.text?.toString() ?: "",
                    categoryId = category.id
                )
            }
            group.addView(chip)
        }

        syncSelectedChip()
    }

    private fun createChip(
        label: String,
        emoji: String,
        isSelected: Boolean,
        accentColor: Int
    ): Chip {
        return Chip(requireContext()).apply {
            text = "$emoji  $label"
            isCheckable = false

            chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                if (isSelected) accentColor else 0xFFF5F5F5.toInt()
            )
            setTextColor(if (isSelected) Color.WHITE else 0xFF666666.toInt())

            chipMinHeight  = dpToPx(36).toFloat()
            textSize       = 13f
            chipStartPadding  = dpToPx(12).toFloat()
            chipEndPadding    = dpToPx(12).toFloat()
            chipCornerRadius  = dpToPx(20).toFloat()

            tag = accentColor
        }
    }

    private fun selectChip(group: ChipGroup, selected: Chip) {
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip ?: continue
            val accent = chip.tag as? Int ?: requireContext().getColor(R.color.primary)
            if (chip == selected) {
                chip.chipBackgroundColor =
                    android.content.res.ColorStateList.valueOf(accent)
                chip.setTextColor(Color.WHITE)
            } else {
                chip.chipBackgroundColor =
                    android.content.res.ColorStateList.valueOf(0xFFF5F5F5.toInt())
                chip.setTextColor(0xFF666666.toInt())
            }
        }
        binding.chipScrollView.post {
            binding.chipScrollView.smoothScrollTo(selected.left - dpToPx(16), 0)
        }
    }

    private fun syncSelectedChip() {
        val currentId = viewModel.selectedCategoryId ?: return
        val group = binding.chipGroup
        for (i in 1 until group.childCount) {
            if (loadedCategories.getOrNull(i - 1)?.id == currentId) {
                val chip = group.getChildAt(i) as? Chip ?: break
                selectChip(group, chip)
                break
            }
        }
    }

    private fun buildCategoryGrid(categories: List<CategoryResponse>) {
        val container = binding.llCategoriesGrid
        container.removeAllViews()

        categories.chunked(2).forEachIndexed { rowIndex, pair ->
            val rowLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            pair.forEachIndexed { colIndex, category ->
                val tile = buildCategoryTile(
                    category = category,
                    colorInt = CATEGORY_COLORS[(rowIndex * 2 + colIndex) % CATEGORY_COLORS.size]
                )
                tile.layoutParams = LinearLayout.LayoutParams(0, dpToPx(92), 1f).apply {
                    marginStart = if (colIndex == 0) 0 else dpToPx(5)
                    topMargin    = dpToPx(5)
                    bottomMargin = dpToPx(5)
                }
                rowLayout.addView(tile)
            }

            if (pair.size == 1) {
                rowLayout.addView(View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(0, dpToPx(92), 1f).apply {
                        marginStart = dpToPx(5)
                    }
                })
            }

            container.addView(rowLayout)
        }
    }

    private fun buildCategoryTile(category: CategoryResponse, colorInt: Int): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            radius = dpToPx(16).toFloat()
            cardElevation = 0f
            setCardBackgroundColor(colorInt)
            isClickable = true
            isFocusable = true
            foreground = requireContext()
                .obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
                .getDrawable(0)
        }

        val inner = FrameLayout(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val overlay = View(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#22000000"))
        }

        val nameView = android.widget.TextView(requireContext()).apply {
            text = category.name
            setTextColor(Color.WHITE)
            textSize = 13.5f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setShadowLayer(4f, 0f, 1f, 0x55000000)
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.BOTTOM or android.view.Gravity.START
                setMargins(dpToPx(12), 0, dpToPx(12), dpToPx(12))
            }
        }

        val emojiView = android.widget.TextView(requireContext()).apply {
            text = resolveEmoji(category.name)
            textSize = 30f
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.TOP or android.view.Gravity.END
                setMargins(0, dpToPx(10), dpToPx(10), 0)
            }
        }

        inner.addView(overlay)
        inner.addView(nameView)
        inner.addView(emojiView)
        card.addView(inner)

        card.setOnClickListener {
            showChipRow()
            binding.btnCancel.show()
            viewModel.searchByCategory(category)
            syncSelectedChip()
        }

        return card
    }

    private fun hideAllStates() {
        binding.layoutEmptyState.hide()
        binding.layoutResults.hide()
        binding.layoutNoResults.hide()
        binding.progressBar.hide()
    }

    private fun showEmptyState() {
        if (viewModel.searchState.value == null) {
            hideAllStates()
            binding.layoutEmptyState.show()
        }
    }

    private fun showResults() {
        hideAllStates()
        binding.layoutResults.show()
    }

    private fun showNoResults() {
        hideAllStates()
        val query = binding.etSearch.text?.toString() ?: ""
        binding.tvNoResultsQuery.text = if (query.isNotBlank()) "para \"$query\"" else ""
        binding.layoutNoResults.show()
    }


    private fun resolveEmoji(name: String): String {
        val lower = name.lowercase()
        return CATEGORY_EMOJIS.entries.firstOrNull { lower.contains(it.key) }?.value ?: "🛍️"
    }

    private fun dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
    private fun Float.dpToPx(): Float = this * resources.displayMetrics.density

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}