package com.ecommerce.app.ui.customer.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ecommerce.app.R
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.databinding.FragmentHomeBinding
import com.ecommerce.app.ui.customer.products.ProductAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val TILE_COLORS = listOf(
    0xFF1DB954.toInt(),
    0xFFFF8C00.toInt(),
    0xFFE91E8C.toInt(),
    0xFF3D5AFE.toInt(),
    0xFFD32F2F.toInt(),
    0xFF00BCD4.toInt(),
    0xFF8E24AA.toInt(),
    0xFF388E3C.toInt(),
    0xFFFF6B35.toInt(),
    0xFF0277BD.toInt(),
)

private val CATEGORY_EMOJIS = mapOf(
    "lanche" to "🍔", "burger" to "🍔",
    "pizza" to "🍕",
    "sushi" to "🍣", "japonesa" to "🍣",
    "bebida" to "🥤",
    "doce" to "🍰", "sobremesa" to "🍰", "bolo" to "🎂",
    "mercado" to "🛒",
    "farmácia" to "💊", "farmacia" to "💊",
    "açaí" to "🫐", "acai" to "🫐",
    "fit" to "💪", "saudável" to "🥗",
    "mexicana" to "🌮",
    "italiana" to "🍝",
    "frango" to "🍗",
    "vegano" to "🌱",
    "gourmet" to "⭐",
    "chinesa" to "🥡",
    "árabe" to "🥙",
)

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flCartContainer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.tvSeeMoreCategories.setOnClickListener {
            navigateToSearch()
        }

        viewModel.loadCategories()
        viewModel.loadCart()

        loadFirstName()
        setupBanner()
        setupSwipeRefresh()
        observeCategories()
        observeProductsByCategory()
        observeCart()
    }

    // ── Navigation ────────────────────────────────────────────────────────────

    private fun navigateToSearch(categoryId: Long? = null) {
        requireActivity()
            .findViewById<BottomNavigationView>(R.id.bottom_nav_customer)
            ?.selectedItemId = R.id.bottom_nav_customer

        if (categoryId != null) {
            findNavController()
                .currentBackStackEntry
                ?.savedStateHandle
                ?.set("categoryId", categoryId)
        }
    }

    // ── Banner ────────────────────────────────────────────────────────────────

    private fun setupBanner() {
        val banners = listOf(
            BannerItem(R.drawable.img_banner_1),
            BannerItem(R.drawable.img_banner_2),
            BannerItem(R.drawable.img_banner_3)
        )
        val bannerAdapter = BannerAdapter(banners)
        binding.vpBanner.apply {
            adapter = bannerAdapter
            offscreenPageLimit = 1
            clipChildren = false
            setPageTransformer { page, position ->
                page.scaleY = 1 - 0.05f * kotlin.math.abs(position)
                page.alpha = 1 - 0.3f * kotlin.math.abs(position)
            }
        }
        setupDots(banners.size)
        binding.vpBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = updateDots(position, banners.size)
        })
        startAutoScroll(bannerAdapter)
    }

    private fun setupDots(count: Int) {
        binding.llDots.removeAllViews()
        repeat(count) {
            val dot = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(8.dp, 8.dp).also { p -> p.marginEnd = 6.dp }
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_dot_inactive)
            }
            binding.llDots.addView(dot)
        }
        updateDots(0, count)
    }

    private fun updateDots(selected: Int, count: Int) {
        for (i in 0 until count) {
            binding.llDots.getChildAt(i)?.background = ContextCompat.getDrawable(
                requireContext(),
                if (i == selected) R.drawable.bg_dot_active else R.drawable.bg_dot_inactive
            )
        }
    }

    // ── Categories ────────────────────────────────────────────────────────────

    private fun observeCategories() {
        viewModel.categoriesState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val categories = result.data.content
                buildCategoryTiles(categories)
                viewModel.loadProductsByCategories(categories)
            }
        }
    }

    private fun buildCategoryTiles(categories: List<CategoryResponse>) {
        val container = binding.llCategoryTiles
        container.removeAllViews()

        val rest = if (categories.size > 1) categories.subList(1, categories.size) else emptyList()
        rest.chunked(2).forEachIndexed { rowIdx, pair ->
            val row = buildRow(
                left = buildTile(pair[0], (rowIdx * 2 + 1) % TILE_COLORS.size),
                right = if (pair.size > 1) buildTile(pair[1], (rowIdx * 2 + 2) % TILE_COLORS.size) else null
            )
            container.addView(row)
        }
    }

    private fun buildTile(category: CategoryResponse, colorIndex: Int): MaterialCardView {
        return buildColoredTile(
            label = category.name,
            emoji = resolveEmoji(category.name),
            colorInt = TILE_COLORS[colorIndex % TILE_COLORS.size],
            onClick = { navigateToSearch(category.id) }
        )
    }

    private fun buildColoredTile(
        label: String,
        emoji: String,
        colorInt: Int,
        onClick: () -> Unit
    ): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            radius = 16.dp.toFloat()
            cardElevation = 0f
            setCardBackgroundColor(colorInt)
            isClickable = true
            isFocusable = true
            foreground = requireContext().obtainStyledAttributes(
                intArrayOf(android.R.attr.selectableItemBackground)
            ).getDrawable(0)
        }

        val frame = FrameLayout(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val scrim = View(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#1A000000"))
        }

        val nameView = TextView(requireContext()).apply {
            text = label
            setTextColor(Color.WHITE)
            textSize = 13.5f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setShadowLayer(4f, 0f, 1f, 0x66000000)
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.BOTTOM or android.view.Gravity.START
                setMargins(12.dp, 0, 12.dp, 12.dp)
            }
        }

        val emojiView = TextView(requireContext()).apply {
            text = emoji
            textSize = 28f
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.TOP or android.view.Gravity.END
                setMargins(0, 8.dp, 10.dp, 0)
            }
        }

        frame.addView(scrim)
        frame.addView(nameView)
        frame.addView(emojiView)
        card.addView(frame)
        card.setOnClickListener { onClick() }
        return card
    }

    private fun buildRow(left: MaterialCardView, right: MaterialCardView?): LinearLayout {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val rowParams = LinearLayout.LayoutParams(0, 90.dp, 1f).apply {
                topMargin = 5.dp
                bottomMargin = 5.dp
            }
            left.layoutParams = rowParams
            addView(left)

            if (right != null) {
                val rightParams = LinearLayout.LayoutParams(0, 90.dp, 1f).apply {
                    marginStart = 6.dp
                    topMargin = 5.dp
                    bottomMargin = 5.dp
                }
                right.layoutParams = rightParams
                addView(right)
            } else {
                addView(View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(0, 90.dp, 1f).apply {
                        marginStart = 6.dp
                    }
                })
            }
        }
    }

    // ── Products by category ──────────────────────────────────────────────────

    private fun observeProductsByCategory() {
        viewModel.productsByCategory.observe(viewLifecycleOwner) { grouped ->
            binding.swipeRefresh.isRefreshing = false

            if (grouped.isEmpty()) {
            } else {
                buildCategorySections(grouped)
            }
        }
    }

    private fun buildCategorySections(grouped: Map<CategoryResponse, List<ProductResponse>>) {
        val container = binding.llCategoriesContainer
        container.removeAllViews()

        grouped.forEach { (category, products) ->
            if (products.isEmpty()) return@forEach

            val sectionView =
                layoutInflater.inflate(R.layout.item_category_section, container, false)
            sectionView.findViewById<TextView>(R.id.tv_category_name).text = category.name

            val rv = sectionView.findViewById<RecyclerView>(R.id.rv_category_products)
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rv.adapter = ProductAdapter { product ->
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundleOf("productId" to product.id)
                )
            }.also { it.submitList(products) }

            container.addView(sectionView)
        }
    }

    // ── Auto-scroll banner ────────────────────────────────────────────────────

    private fun startAutoScroll(adapter: BannerAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    delay(5000)
                    val vp = _binding?.vpBanner ?: break
                    vp.setCurrentItem((vp.currentItem + 1) % adapter.itemCount, true)
                }
            }
        }
    }

    // ── Misc observers ────────────────────────────────────────────────────────

    private fun loadFirstName() {
        viewModel.firstName.observe(viewLifecycleOwner) { result ->
            binding.tvFirstName.text = when (result) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> "Erro ao carregar"
                is NetworkResult.Loading -> "Carregando..."
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadCategories()
            viewModel.loadCart()
        }
    }

    private fun observeCart() {
        viewModel.cartState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val quantity = result.data.totalQuantity
                if (quantity > 0) {
                    binding.tvCartBadge.text = quantity.toString()
                    binding.tvCartBadge.show()
                } else {
                    binding.tvCartBadge.hide()
                }
            }
        }
    }

    // ── Utilities ─────────────────────────────────────────────────────────────

    private fun resolveEmoji(name: String): String {
        val lower = name.lowercase()
        return CATEGORY_EMOJIS.entries.firstOrNull { lower.contains(it.key) }?.value ?: "🛍️"
    }

    private val Int.dp get() = (this * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}