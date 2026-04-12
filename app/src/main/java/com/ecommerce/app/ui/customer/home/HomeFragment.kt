package com.ecommerce.app.ui.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ecommerce.app.R
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.product.HomeProductsResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.databinding.FragmentHomeBinding
import com.ecommerce.app.ui.customer.products.ProductAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val TILE_COLORS = listOf(
    0xFF1DB954.toInt(), 0xFFFF8C00.toInt(), 0xFFE91E8C.toInt(),
    0xFF3D5AFE.toInt(), 0xFFD32F2F.toInt(), 0xFF00BCD4.toInt(),
    0xFF8E24AA.toInt(), 0xFF388E3C.toInt(), 0xFFFF6B35.toInt(),
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
    private val viewModel: HomeViewModel by activityViewModels()

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

        binding.llCategoryHeader.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        setupBanner()
        setupSwipeRefresh()
        observeGreeting()
        observeCategories()
        observeProductsByCategory()
        observeCart()
        observeLoading()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCart()
    }

    private fun navigateToSearch(categoryId: Long? = null) {
        val navController = findNavController()
        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(navController.graph.startDestinationId, inclusive = false, saveState = true)
            .build()

        val bundle = bundleOf("categoryId" to (categoryId ?: -1L))
        navController.navigate(R.id.action_homeFragment_to_searchFragment, bundle, navOptions)
    }

    private fun observeGreeting() {
        viewModel.firstName.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    binding.tvHi.text = "Olá, "
                    binding.tvFirstName.text = result.data
                }
                is NetworkResult.Error -> {
                    binding.tvFirstName.text = ""
                }
                is NetworkResult.Loading -> {
                    binding.tvFirstName.text = ""
                }
            }
        }
    }

    private fun observeCategories() {
        viewModel.categoriesState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val categories = result.data.content
                buildCategoryTiles(categories)
            }
        }
    }

    private fun observeProductsByCategory() {
        viewModel.productsByCategory.observe(viewLifecycleOwner) { grouped ->
            binding.swipeRefresh.isRefreshing = false
            if (grouped.isNotEmpty()) buildCategorySections(grouped)
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

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.isVisible = loading
            binding.contentLayout.isVisible = !loading
        }
    }

    private fun setupBanner() {
        val banners = listOf(
            BannerItem(R.drawable.img_banner_1),
            BannerItem(R.drawable.img_banner_2),
            BannerItem(R.drawable.img_banner_3),
        )
        val bannerAdapter = BannerAdapter(banners)
        binding.vpBanner.apply {
            adapter = bannerAdapter
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
                layoutParams = LinearLayout.LayoutParams(8.dp, 8.dp)
                    .also { p -> p.marginEnd = 6.dp }
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

    private fun buildCategoryTiles(categories: List<CategoryResponse>) {
        val container = binding.llCategoryTiles
        container.removeAllViews()

        categories.forEachIndexed { index, category ->
            val chip = layoutInflater.inflate(R.layout.item_category_chip, container, false)
            chip.findViewById<MaterialCardView>(R.id.card_category)
                .setCardBackgroundColor(TILE_COLORS[index % TILE_COLORS.size])
            chip.findViewById<TextView>(R.id.tv_category_emoji)
                .text = resolveEmoji(category.name)
            chip.findViewById<TextView>(R.id.tv_category_name)
                .text = category.name
            chip.setOnClickListener { navigateToSearch(category.id) }
            container.addView(chip)
        }
    }

    private fun buildCategorySections(groups: List<HomeProductsResponse>) {
        val container = binding.llCategoriesContainer
        container.removeAllViews()

        groups.forEach { (category, products) ->
            if (products.isEmpty()) return@forEach

            val sectionView = layoutInflater.inflate(
                R.layout.item_category_section, container, false
            )
            sectionView.findViewById<TextView>(R.id.tv_category_name).text = category.name

            sectionView.findViewById<View>(R.id.ll_category_header).setOnClickListener {
                navigateToSearch(category.id)
            }

            val rv = sectionView.findViewById<RecyclerView>(R.id.rv_category_products)
            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rv.adapter = ProductAdapter { product ->
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundleOf("productId" to product.id)
                )
            }.also { it.submitList(products) }

            container.addView(sectionView)
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }


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