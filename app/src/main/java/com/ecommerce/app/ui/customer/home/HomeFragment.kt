package com.ecommerce.app.ui.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.chip.Chip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        binding.ivCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        observeFirstName()
        setupBanner()
        setupSwipeRefresh()
        observeCategories()
        observeProductsByCategory()

        viewModel.loadCategories()
    }

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
                page.scaleY = 1 - (0.05f * kotlin.math.abs(position))
                page.alpha = 1 - (0.3f * kotlin.math.abs(position))
            }
        }

        setupDots(banners.size)

        binding.vpBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position, banners.size)
            }
        })
        startAutoScroll(bannerAdapter)
    }

    private fun setupDots(count: Int) {
        binding.llDots.removeAllViews()
        repeat(count) {
            val dot = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(8.dp, 8.dp).also { params ->
                    params.marginEnd = 6.dp
                }
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_dot_inactive)
            }
            binding.llDots.addView(dot)
        }
        updateDots(0, count)
    }

    private fun updateDots(selected: Int, count: Int) {
        for (i in 0 until count) {
            val dot = binding.llDots.getChildAt(i) ?: continue
            dot.background = ContextCompat.getDrawable(
                requireContext(),
                if (i == selected) R.drawable.bg_dot_active else R.drawable.bg_dot_inactive
            )
        }
    }

    private fun observeFirstName() {
        viewModel.firstName.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> binding.tvFirstName.text = result.data
                is NetworkResult.Error   -> binding.tvFirstName.text = "Olá!"
                is NetworkResult.Loading -> { }
            }
        }
    }

    private fun observeCategories() {
        viewModel.categoriesState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val categories = result.data.content
                setupCategoryChips(categories)
                viewModel.loadProductsByCategories(categories)
            }
        }
    }

    private fun observeProductsByCategory() {
        viewModel.productsByCategory.observe(viewLifecycleOwner) { grouped ->
            binding.swipeRefresh.isRefreshing = false
            binding.progressBar.hide()

            if (grouped.isEmpty()) {
                binding.tvEmpty.show()
            } else {
                binding.tvEmpty.hide()
                buildCategorySections(grouped)
            }
        }
    }

    private fun buildCategorySections(grouped: Map<CategoryResponse, List<ProductResponse>>) {
        val container = binding.llCategoriesContainer
        container.removeAllViews()

        grouped.forEach { (category, products) ->
            if (products.isEmpty()) return@forEach

            val sectionView = layoutInflater.inflate(
                R.layout.item_category_section, container, false
            )

            sectionView.findViewById<TextView>(R.id.tv_category_name).text = category.name

            val rv = sectionView.findViewById<RecyclerView>(R.id.rv_category_products)
            rv.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            rv.adapter = ProductAdapter { product ->
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundleOf("productId" to product.id)
                )
            }.also { it.submitList(products) }

            container.addView(sectionView)
        }
    }

    private fun setupCategoryChips(categories: List<CategoryResponse>) {
        val chipGroup = binding.chipGroupCategories
        chipGroup.removeAllViews()

        categories.forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category.name
                tag = category
                isCheckable = true
                setChipBackgroundColorResource(R.color.chip_background)
                setTextColor(resources.getColorStateList(R.color.chip_text_color, null))
            }
            chipGroup.addView(chip)
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                val allCategories = (0 until group.childCount)
                    .map { group.getChildAt(it).tag as CategoryResponse }
                viewModel.loadProductsByCategories(allCategories)
            } else {
                val selected = group.findViewById<Chip>(checkedIds.first()).tag as CategoryResponse
                viewModel.loadProductsByCategories(listOf(selected))
            }
        }
    }

    private fun startAutoScroll(adapter: BannerAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                while (true) {
                    delay(5000)

                    val vp = _binding?.vpBanner ?: break

                    val next = (vp.currentItem + 1) % adapter.itemCount
                    vp.setCurrentItem(next, true)
                }
            }
        }
    }
    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadCategories()
        }
    }

    private val Int.dp get() = (this * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}