package com.ecommerce.app.ui.customer.search

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.databinding.FragmentSearchBinding
import com.ecommerce.app.ui.customer.products.ProductAdapter
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
    0xFF8E24AA.toInt(), 0xFFD32F2F.toInt(), 0xFF2196F3.toInt(), 0xFF388E3C.toInt(),
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

    private lateinit var resultsAdapter: ProductAdapter
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
    }

    private fun setupResultsList() {
        resultsAdapter = ProductAdapter { product ->
            findNavController().navigate(
                R.id.action_searchFragment_to_productDetailFragment,
                bundleOf("productId" to product.id)
            )
        }
        binding.rvSearchResults.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = resultsAdapter
        }
    }

    private fun setupSearchBar() {
        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.chipScrollView.show()
                binding.chipScrollView.animate().alpha(1f).translationY(0f).setDuration(200).start()
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

        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.btnCancel.show()
                showChipRow()
            } else {
                if (binding.etSearch.text.isNullOrBlank() && viewModel.selectedCategoryId == null) {
                    binding.btnCancel.hide()
                    hideChipRow()
                }
            }
        }
    }

    private fun showChipRow() {
        binding.chipScrollView.apply {
            alpha = 0f
            translationY = -8f.dpToPx()
            show()
            animate().alpha(1f).translationY(0f).setDuration(220).start()
        }
    }

    private fun hideChipRow() {
        binding.chipScrollView.animate()
            .alpha(0f).translationY(-8f.dpToPx())
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
                showEmptyState()
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

    // ── Category Chips ────────────────────────────────────────────────────────

    private fun buildCategoryChips(categories: List<CategoryResponse>) {
        val group = binding.chipGroup
        group.removeAllViews()

        // "Todos" chip
        val allChip = createChip(
            label = "Todos",
            emoji = "🍽️",
            isSelected = viewModel.selectedCategoryId == null
        )
        allChip.setOnClickListener {
            selectChip(group, allChip)
            val currentQuery = binding.etSearch.text?.toString() ?: ""
            viewModel.search(currentQuery, categoryId = null)
        }
        group.addView(allChip)

        // One chip per category
        categories.forEachIndexed { index, category ->
            val chip = createChip(
                label = category.name,
                emoji = resolveEmoji(category.name),
                isSelected = viewModel.selectedCategoryId == category.id,
                accentColor = CATEGORY_COLORS[index % CATEGORY_COLORS.size]
            )
            chip.setOnClickListener {
                selectChip(group, chip)
                val currentQuery = binding.etSearch.text?.toString() ?: ""
                viewModel.search(currentQuery, categoryId = category.id)
            }
            group.addView(chip)
        }
    }

    private fun createChip(
        label: String,
        emoji: String,
        isSelected: Boolean,
        accentColor: Int = 0xFFEA1D2C.toInt() // iFood red default
    ): Chip {
        return Chip(requireContext()).apply {
            text = "$emoji  $label"
            isCheckable = false
            isChecked = false

            val selectedBg = accentColor
            val unselectedBg = 0xFFF5F5F5.toInt()

            if (isSelected) {
                setChipBackgroundColorResource(android.R.color.transparent)
                chipBackgroundColor = android.content.res.ColorStateList.valueOf(selectedBg)
                setTextColor(Color.WHITE)
                chipStrokeWidth = 0f
            } else {
                chipBackgroundColor = android.content.res.ColorStateList.valueOf(unselectedBg)
                setTextColor(0xFF666666.toInt())
                chipStrokeWidth = 0f
            }

            chipMinHeight = dpToPx(36).toFloat()
            textSize = 13f
            chipStartPadding = dpToPx(12).toFloat()
            chipEndPadding = dpToPx(12).toFloat()
            chipCornerRadius = dpToPx(20).toFloat()
            tag = accentColor
        }
    }

    /** Visually marks [selected] as active and resets all others. */
    private fun selectChip(group: ChipGroup, selected: Chip) {
        val allChip = group.getChildAt(0) as? Chip
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip ?: continue
            val isAll = chip == allChip
            val accent = if (isAll) 0xFFEA1D2C.toInt() else (chip.tag as? Int ?: 0xFFEA1D2C.toInt())
            if (chip == selected) {
                chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(accent)
                chip.setTextColor(Color.WHITE)
            } else {
                chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(0xFFF5F5F5.toInt())
                chip.setTextColor(0xFF666666.toInt())
            }
        }
        // Scroll the selected chip into view
        binding.chipScrollView.post {
            val x = selected.left - dpToPx(16)
            binding.chipScrollView.smoothScrollTo(x, 0)
        }
    }

    // ── Category Grid (home state) ────────────────────────────────────────────

    private fun buildCategoryGrid(categories: List<CategoryResponse>) {
        val container = binding.llCategoriesGrid
        container.removeAllViews()

        val rows = categories.chunked(2)
        rows.forEachIndexed { rowIndex, pair ->
            val rowLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            pair.forEachIndexed { colIndex, category ->
                val tileView = buildCategoryTile(
                    category = category,
                    colorInt = CATEGORY_COLORS[(rowIndex * 2 + colIndex) % CATEGORY_COLORS.size]
                )
                val params = LinearLayout.LayoutParams(0, dpToPx(92), 1f).apply {
                    marginStart = if (colIndex == 0) 0 else dpToPx(5)
                    topMargin = dpToPx(5)
                    bottomMargin = dpToPx(5)
                }
                tileView.layoutParams = params
                rowLayout.addView(tileView)
            }

            if (pair.size == 1) {
                val spacer = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(0, dpToPx(92), 1f).apply {
                        marginStart = dpToPx(5)
                    }
                }
                rowLayout.addView(spacer)
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
            foreground = requireContext().obtainStyledAttributes(
                intArrayOf(android.R.attr.selectableItemBackground)
            ).getDrawable(0)
        }

        val inner = FrameLayoutCompat(requireContext())
        inner.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val overlay = View(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#22000000"))
        }

        val nameView = android.widget.TextView(requireContext()).apply {
            text = category.name
            setTextColor(Color.WHITE)
            textSize = 13.5f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setShadowLayer(4f, 0f, 1f, 0x55000000)
            layoutParams = android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.BOTTOM or android.view.Gravity.START
                setMargins(dpToPx(12), 0, dpToPx(12), dpToPx(12))
            }
        }

        val emojiView = android.widget.TextView(requireContext()).apply {
            text = resolveEmoji(category.name)
            textSize = 30f
            layoutParams = android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
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
            // Focus search bar, select chip, fetch
            binding.etSearch.requestFocus()
            binding.etSearch.setText("")
            showChipRow()
            binding.btnCancel.show()

            val group = binding.chipGroup
            for (i in 1 until group.childCount) {
                val chip = group.getChildAt(i) as? Chip ?: continue
                if (loadedCategories.getOrNull(i - 1)?.id == category.id) {
                    selectChip(group, chip)
                    break
                }
            }
            viewModel.searchByCategory(category)
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
        hideAllStates()
        binding.layoutEmptyState.show()
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

private class FrameLayoutCompat(context: android.content.Context) :
    android.widget.FrameLayout(context)